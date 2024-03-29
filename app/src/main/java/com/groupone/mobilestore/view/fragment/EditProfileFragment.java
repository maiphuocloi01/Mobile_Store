package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.NumberUtils.convertDateType1;
import static com.groupone.mobilestore.util.NumberUtils.convertDateType2;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentEditProfileBinding;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.viewmodel.AccountViewModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class EditProfileFragment extends BaseFragment<FragmentEditProfileBinding, AccountViewModel> {

    public static final String TAG = EditProfileFragment.class.getName();
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher;
    private int defaultYear = 2001;
    private int defaultMonth = 0;
    private int defaultDay = 1;
    private Object mData;
    private String filePath = null;
    private User user;

    public static boolean verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {

                            ContentResolver resolver = context.getContentResolver();
                            Intent data = result.getData();

                            Bitmap bitmap = null;
                            try {
                                if (data != null) {
                                    filePath = getRealPathFromURI(data.getData());
                                    bitmap = MediaStore.Images.Media.getBitmap(resolver, data.getData());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            displayImage(bitmap);
                        }
                    }
                });
    }

    @Override
    protected Class<AccountViewModel> getClassVM() {
        return AccountViewModel.class;
    }

    @Override
    protected void initViews() {


        Log.d(TAG, "initViews: " + filePath);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        binding.btChangeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeImageFromAlbumWithIntent();
            }
        });

        user = (User) mData;

        defaultDay = Integer.parseInt(user.getBirthday().substring(0,2));
        defaultMonth = Integer.parseInt(user.getBirthday().substring(3,5)) - 1;
        defaultYear = Integer.parseInt(user.getBirthday().substring(user.getBirthday().length() - 4));

        Log.d(TAG, user.toString());
        binding.etName.setText(user.getFullName());
        binding.etPhone.setText(user.getPhoneNumber());
        binding.etEmail.setText(user.getEmail());
        binding.etBirthday.setText(user.getBirthday());
        binding.etBirthday.setCursorVisible(false);
        binding.etBirthday.setShowSoftInputOnFocus(false);
        if (user.isGender()) {
            binding.rbFemale.setChecked(true);
        } else {
            binding.rbMale.setChecked(true);
        }
        if (filePath == null) {
            Glide.with(context).load(user.getAvatar()).into(binding.ivAvatar);
        }

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = binding.etName.getText().toString();
                String newPhone = binding.etPhone.getText().toString();
                String newEmail = binding.etEmail.getText().toString();
                String newBirthday = binding.etBirthday.getText().toString();
                boolean newGender = binding.rbFemale.isChecked();
                String imageFileName = null;

                if (newName.equals("")) {
                    binding.etName.setError("Vui lòng nhập thông tin");
                } else if (newPhone.equals("")) {
                    binding.etPhone.setError("Vui lòng nhập thông tin");
                } else if (newEmail.equals("")) {
                    binding.etEmail.setError("Vui lòng nhập thông tin");
                } else if (newBirthday.equals("")) {
                    binding.etBirthday.setError("Vui lòng nhập thông tin");
                } else if (!newName.equals(user.getFullName()) ||
                        !newPhone.equals(user.getPhoneNumber()) ||
                        !newEmail.equals(user.getEmail()) ||
                        !newBirthday.equals(user.getBirthday()) ||
                        newGender != user.isGender() ||
                        filePath != null
                ) {
                    DialogUtils.showLoadingDialog(context);

                    if (filePath != null) {
                        Log.d(TAG, filePath);
                        File file = new File(filePath);
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                        String suffix = file.getName().substring(file.getName().lastIndexOf("."));
                        imageFileName = user.getUserName() + "_" + timeStamp + suffix;
                        Log.d(TAG, file.toString());
                        Log.d(TAG, file.getName());
                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                        MultipartBody.Part parts = MultipartBody.Part.createFormData("newimage", imageFileName, requestBody);

                        RequestBody someData = RequestBody.create(MediaType.parse("text/plain"), "This is a new Image");
                        viewModel.uploadImageAccount(parts, someData);

                    }
                    Log.d(TAG, "onClick: " + imageFileName + " / " + newName + " / " + newBirthday + " / " + newEmail + " / " + newGender + " / " + newPhone);
                    viewModel.updateAccount(newName, newEmail, newPhone, newBirthday, newGender, imageFileName);


                } else {
                    Toast.makeText(context, "Không có thông tin nào thay đổi", Toast.LENGTH_SHORT).show();
                }


            }
        });

        binding.etBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    showDatePickerDialog();
                }
            }
        });

        binding.etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        binding.btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(ChangePasswordFragment.TAG, null, true);
            }
        });

    }

    private void showDatePickerDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_date_picker);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        dialog.setCancelable(true);

        Button btnCancel = dialog.findViewById(R.id.bt_cancel2);
        Button btnConfirm = dialog.findViewById(R.id.bt_confirm2);
        TextView tvDate = dialog.findViewById(R.id.tv_date);
        DatePicker picker = dialog.findViewById(R.id.datePicker);
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(System.currentTimeMillis());

        tvDate.setText(convertDateType1(defaultDay, defaultMonth + 1, defaultYear));
        picker.init(defaultYear, defaultMonth, defaultDay, new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                tvDate.setText(convertDateType1(dayOfMonth, month + 1, year));
                defaultDay = dayOfMonth;
                defaultMonth = month;
                defaultYear = year;
            }
        });

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnConfirm.setOnClickListener(view -> {
            binding.etBirthday.setText(convertDateType2(picker.getDayOfMonth(), picker.getMonth(), picker.getYear()));
            dialog.dismiss();
        });
        dialog.show();
    }

    public void takeImageFromAlbumWithIntent() {
        if (verifyStoragePermissions(getActivity())) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            someActivityResultLauncher.launch(intent);
        } else {
            Toast.makeText(context, "Bạn chưa có quyền truy cập", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayImage(Bitmap bitmap) {
        binding.ivAvatar.setImageBitmap(bitmap);
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    @Override
    protected FragmentEditProfileBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentEditProfileBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_UPLOAD_IMAGE)) {
            ResponseBody res = (ResponseBody) data;
            Log.d(TAG, res.toString());
        } else if (key.equals(Constants.KEY_UPDATE_ACCOUNT)) {
            Boolean check = (Boolean) data;
            if (check) {
                Toast.makeText(context, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                viewModel.getUserByUserName(user.getUserName());
            } else {
                Toast.makeText(context, "Cập nhật thông tin thất bại", Toast.LENGTH_SHORT).show();
            }

        }
        else if(key.equals(Constants.KEY_GET_BY_USERNAME)){
            MyApplication.getInstance().getStorage().user = (User) data;
            Log.d(TAG, "apiSuccess: " + ((User) data).toString());
            callBack.backToPrev();
        }
        DialogUtils.hideLoadingDialog();
    }

    @Override
    public void apiError(String key, int code, Object data) {
        DialogUtils.hideLoadingDialog();
        if (code == 999) {
            Log.d(TAG, "apiError: " + data.toString());
            Toast.makeText(context, "Không thể kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }
}

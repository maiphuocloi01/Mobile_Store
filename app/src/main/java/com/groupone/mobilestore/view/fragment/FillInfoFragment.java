package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.NumberUtils.convertDateType1;
import static com.groupone.mobilestore.util.NumberUtils.convertDateType2;
import static com.groupone.mobilestore.viewmodel.AccountViewModel.KEY_CHECK_REGISTER;
import static com.groupone.mobilestore.viewmodel.AccountViewModel.KEY_REGISTER;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.MediaStore;
import android.text.TextUtils;
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

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentFillInfoBinding;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.viewmodel.AccountViewModel;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.io.IOException;
import java.util.List;

public class FillInfoFragment extends BaseFragment<FragmentFillInfoBinding, AccountViewModel> {

    public static final String TAG = FillInfoFragment.class.getName();

    private int defaultYear = 2000;
    private int defaultMonth = 0;
    private int defaultDay = 1;
    private Object mData;
    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher;

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

        binding.btAddAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeImageFromAlbumWithIntent();
            }
        });
        binding.etBirthday.setCursorVisible(false);
        binding.etBirthday.setShowSoftInputOnFocus(false);

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
                //hideSoftInput(view);
                //toggleSoftInput(context);
                showDatePickerDialog();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(binding.etName.getText())){
                    binding.etName.setError("Không được bỏ trống");

                } else if (TextUtils.isEmpty(binding.etPhoneNumber.getText())){
                    binding.etPhoneNumber.setError("Không được bỏ trống");

                } else if (TextUtils.isEmpty(binding.etBirthday.getText())){
                    binding.etBirthday.setError("Không được bỏ trống");

                } else if(!binding.rbMale.isChecked() && !binding.rbFemale.isChecked()){
                    Toast.makeText(context, "Chọn giới tính", Toast.LENGTH_SHORT).show();
                } else {
                    boolean gender = false;
                    List<String> listInfo = (List<String>) mData;
                    if (binding.rbFemale.isChecked()){
                        gender = true;
                    }
                    viewModel.register(
                            listInfo.get(1),
                            binding.etName.getText().toString(),
                            listInfo.get(0),
                            binding.etPhoneNumber.getText().toString(),
                            binding.etBirthday.getText().toString(),
                            gender,
                            null,
                            listInfo.get(2)
                    );
                    DialogUtils.showLoadingDialog(context);
                    //callBack.showFragment(LoginFragment.TAG, null, false);
                }
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
                //Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
                tvDate.setText(convertDateType1(dayOfMonth, month + 1, year));
                defaultDay = dayOfMonth;
                defaultMonth = month;
                defaultYear = year;

            }
        });

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnConfirm.setOnClickListener(view -> {
            //binding.etBirthday.setText(picker.getDayOfMonth() + "/" + (picker.getMonth() + 1) + "/" + picker.getYear());
            binding.etBirthday.setText(convertDateType2(picker.getDayOfMonth(), picker.getMonth(), picker.getYear()));
            dialog.dismiss();
        });


        dialog.show();
    }

    public void takeImageFromAlbumWithIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        someActivityResultLauncher.launch(intent);
    }

    private void displayImage(Bitmap bitmap) {
        binding.ivAvatar.setImageBitmap(bitmap);
    }

    @Override
    protected FragmentFillInfoBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFillInfoBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if(key.equals(KEY_REGISTER)){
            int response = (int) data;
            Log.d(TAG, "apiSuccess: " + response);
            if(response == -1){
                Toast.makeText(context, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                callBack.backToPrev();
            } else {
                Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                callBack.showFragment(LoginFragment.TAG, null, false);
            }
            DialogUtils.hideLoadingDialog();
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        Log.d(TAG, "apiSuccess: " + data.toString());
        DialogUtils.hideLoadingDialog();
        Toast.makeText(context, "Error: " + code + ", " + data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }
}

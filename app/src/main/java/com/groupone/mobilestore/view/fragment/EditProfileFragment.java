package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.NumberUtils.convertDateType1;
import static com.groupone.mobilestore.util.NumberUtils.convertDateType2;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentEditProfileBinding;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.io.IOException;

public class EditProfileFragment extends BaseFragment<FragmentEditProfileBinding, CommonViewModel> {

    public static final String TAG = EditProfileFragment.class.getName();

    private int defaultYear = 2001;
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
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

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

        User user = (User) mData;

        Log.d(TAG, user.toString());
        binding.etName.setText(user.getFullName());
        binding.etPhone.setText(user.getPhoneNumber());
        binding.etEmail.setText(user.getEmail());
        binding.etBirthday.setText(user.getBirthday());
        binding.etBirthday.setCursorVisible(false);
        binding.etBirthday.setShowSoftInputOnFocus(false);
        if(user.isGender()){
            binding.rbFemale.setChecked(true);
        } else {
            binding.rbMale.setChecked(true);
        }
        Glide.with(context).load(user.getAvatar()).into(binding.ivAvatar);

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
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
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        someActivityResultLauncher.launch(intent);
    }

    private void displayImage(Bitmap bitmap) {
        binding.ivAvatar.setImageBitmap(bitmap);
    }

    @Override
    protected FragmentEditProfileBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentEditProfileBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }
}

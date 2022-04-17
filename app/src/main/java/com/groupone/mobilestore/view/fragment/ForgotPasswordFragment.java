package com.groupone.mobilestore.view.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentForgotPasswordBinding;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class ForgotPasswordFragment extends BaseFragment<FragmentForgotPasswordBinding, CommonViewModel> {

    public static final String TAG = ForgotPasswordFragment.class.getName();

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {
        binding.btSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSendOTPDialog();
            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });
    }

    private void openSendOTPDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_confirm_otp);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        dialog.setCancelable(false);

        Button btnCancel = dialog.findViewById(R.id.bt_cancel);
        Button btnConfirm = dialog.findViewById(R.id.bt_confirm);

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnConfirm.setOnClickListener(view -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    protected FragmentForgotPasswordBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentForgotPasswordBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}

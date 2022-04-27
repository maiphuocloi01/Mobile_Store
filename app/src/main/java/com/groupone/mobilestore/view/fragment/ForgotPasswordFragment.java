package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.viewmodel.AccountViewModel.KEY_SEND_OTP;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentForgotPasswordBinding;
import com.groupone.mobilestore.viewmodel.AccountViewModel;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class ForgotPasswordFragment extends BaseFragment<FragmentForgotPasswordBinding, AccountViewModel> {

    public static final String TAG = ForgotPasswordFragment.class.getName();
    private String otp;

    @Override
    protected Class<AccountViewModel> getClassVM() {
        return AccountViewModel.class;
    }

    @Override
    protected void initViews() {
        binding.btSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.etEmail.getText())) {
                    binding.etEmail.setError("Bạn chưa nhập email");
                } else {
                    sendOTP();
                    openSendOTPDialog();
                }
            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });
    }

    private void sendOTP() {
        viewModel.sendOTP(binding.etEmail.getText().toString());
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

        TextView tvEmail = dialog.findViewById(R.id.tv_email);
        TextView tvResend = dialog.findViewById(R.id.tv_resend);
        EditText etOtp = dialog.findViewById(R.id.et_otp);
        Button btnCancel = dialog.findViewById(R.id.bt_cancel);
        Button btnConfirm = dialog.findViewById(R.id.bt_confirm);

        tvEmail.setText(binding.etEmail.getText());

        tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOTP();
            }
        });

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnConfirm.setOnClickListener(view -> {
            if (TextUtils.isEmpty(etOtp.getText())) {
                etOtp.setError("Bạn cần nhập mã OTP");
            } else if (etOtp.getText().toString().equals(otp)) {
                Toast.makeText(context, "Xác thực thành công", Toast.LENGTH_SHORT).show();
                callBack.showFragment(ResetPasswordFragment.TAG, null, false);
                dialog.dismiss();
            } else {
                Toast.makeText(context, "Mã xác thực không đúng", Toast.LENGTH_SHORT).show();
            }

        });

        dialog.show();
    }

    @Override
    protected FragmentForgotPasswordBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentForgotPasswordBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(KEY_SEND_OTP)) {
            otp = (String) data;
            Log.d(TAG, otp);
            Toast.makeText(context, otp, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        Toast.makeText(context, "Error: " + code + data.toString(), Toast.LENGTH_SHORT).show();
    }
}

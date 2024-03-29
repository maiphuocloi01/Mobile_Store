package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.Constants.KEY_CHECK_EMAIL_EXIST;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.viewmodel.AccountViewModel;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ForgotPasswordFragment extends BaseFragment<FragmentForgotPasswordBinding, AccountViewModel> {

    public static final String TAG = ForgotPasswordFragment.class.getName();
    private String otp;

    @Override
    protected Class<AccountViewModel> getClassVM() {
        return AccountViewModel.class;
    }

    @Override
    protected void initViews() {
        binding.btSendOtp.setOnClickListener(view -> {
            if (TextUtils.isEmpty(binding.etEmail.getText())) {
                binding.etEmail.setError("Vui lòng nhập email");
            } else if(!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.getText()).matches()){
                binding.etEmail.setError("Vui lòng nhập đúng định dạng email");
            } else {
                viewModel.checkEmailExist(binding.etEmail.getText().toString());
                DialogUtils.showLoadingDialog(context);
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

        //Ánh xạ view
        TextView tvEmail = dialog.findViewById(R.id.tv_email);
        TextView tvResend = dialog.findViewById(R.id.tv_resend);
        TextView tvCDTitle = dialog.findViewById(R.id.tv_countdown_title);
        TextView tvCountdown = dialog.findViewById(R.id.tv_countdown);
        EditText etOtp = dialog.findViewById(R.id.et_otp);
        Button btnCancel = dialog.findViewById(R.id.bt_cancel);
        Button btnConfirm = dialog.findViewById(R.id.bt_confirm);

        String email = binding.etEmail.getText().toString();
        tvEmail.setText(email);

        countDownTimer(tvCountdown, tvCDTitle, tvResend);

        tvResend.setOnClickListener(view -> {
            sendOTP();
            tvCountdown.setVisibility(View.VISIBLE);
            tvCDTitle.setVisibility(View.VISIBLE);
            tvResend.setVisibility(View.GONE);
            countDownTimer(tvCountdown, tvCDTitle, tvResend);
        });

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnConfirm.setOnClickListener(view -> {
            if (TextUtils.isEmpty(etOtp.getText())) {
                etOtp.setError("Bạn cần nhập mã OTP");
            } else if (etOtp.getText().toString().equals(otp)) {
                Toast.makeText(context, "Xác thực thành công", Toast.LENGTH_SHORT).show();
                callBack.showFragment(ResetPasswordFragment.TAG, email, false);
                dialog.dismiss();
            } else {
                Toast.makeText(context, "Mã xác thực không đúng", Toast.LENGTH_SHORT).show();
            }

        });

        dialog.show();
    }

    private void countDownTimer(TextView tvCountdown, TextView tvCDTitle, TextView tvResend) {
        long duration = TimeUnit.MINUTES.toMillis(1);
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                String sDuration = String.format(Locale.ENGLISH, "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(l),
                        TimeUnit.MILLISECONDS.toSeconds(l) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l))
                );

                tvCountdown.setText(sDuration);
            }

            @Override
            public void onFinish() {
                tvCountdown.setVisibility(View.GONE);
                tvCDTitle.setVisibility(View.GONE);
                tvResend.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected FragmentForgotPasswordBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentForgotPasswordBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(KEY_CHECK_EMAIL_EXIST)) {
            int response = (int) data;
            if (response == -1) {
                sendOTP();
                openSendOTPDialog();
            } else if (response == -2) {
                Toast.makeText(context, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            } else if (response == 1) {
                Toast.makeText(context, "Email chưa đăng ký", Toast.LENGTH_SHORT).show();
            }
            DialogUtils.hideLoadingDialog();
        }
        if (key.equals(Constants.KEY_SEND_OTP)) {
            otp = (String) data;
            Log.d(TAG, otp);
            //Toast.makeText(context, otp, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        if (key.equals(KEY_CHECK_EMAIL_EXIST)) {
            DialogUtils.hideLoadingDialog();
        }
        Toast.makeText(context, "Không kết nối được máy chủ", Toast.LENGTH_SHORT).show();
    }
}

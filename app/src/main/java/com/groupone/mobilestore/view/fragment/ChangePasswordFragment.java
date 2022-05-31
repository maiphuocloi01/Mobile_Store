package com.groupone.mobilestore.view.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentChangePasswordBinding;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.CommonUtils;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.util.StringConvert;
import com.groupone.mobilestore.viewmodel.AccountViewModel;


public class ChangePasswordFragment extends BaseFragment<FragmentChangePasswordBinding, AccountViewModel> {

    public static final String TAG = ChangePasswordFragment.class.getName();
    private User user = MyApplication.getInstance().getStorage().user;

    @Override
    protected Class<AccountViewModel> getClassVM() {
        return AccountViewModel.class;
    }

    @Override
    protected void initViews() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.etPassword.getText())) {
                    binding.etPassword.setError("Vui lòng nhập mật khẩu");
                } else if (TextUtils.isEmpty(binding.etNewPassword.getText())) {
                    binding.etNewPassword.setError("Vui lòng nhập mật khẩu");
                } else if (TextUtils.isEmpty(binding.etPasswordConfirm.getText())) {
                    binding.etPasswordConfirm.setError("Vui lòng xác nhận mật khẩu");
                } else if (!binding.etNewPassword.getText().toString().equals(binding.etPasswordConfirm.getText().toString())) {
                    Toast.makeText(context, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                } else if (!StringConvert.CreateMD5(binding.etPassword.getText().toString()).equals(user.getPassword())) {
                    Toast.makeText(context, "Mật khẩu hiện tại không chính xác", Toast.LENGTH_SHORT).show();
                } else {
                    DialogUtils.showLoadingDialog(context);
                    //Log.d(TAG, email + " " + binding.etPassword.getText().toString());
                    viewModel.resetPassword(user.getEmail(), binding.etNewPassword.getText().toString().trim());
                }
            }
        });
    }

    @Override
    protected FragmentChangePasswordBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentChangePasswordBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_RESET_PASSWORD)) {
            Boolean isSuccess = (Boolean) data;
            Log.d(TAG, isSuccess.toString());
            if (isSuccess) {
                Toast.makeText(context, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                CommonUtils.getInstance().clearPref(Constants.ACCESS_TOKEN);
                CommonUtils.getInstance().clearPref(Constants.USERNAME);
                callBack.showFragment(LoginFragment.TAG, null, false);
            } else {
                Toast.makeText(context, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
            }
            DialogUtils.hideLoadingDialog();
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        DialogUtils.hideLoadingDialog();
        if (code == 999) {
            Log.d(TAG, "apiError: " + data.toString());
            Toast.makeText(context, "Không thể kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
        }
    }
}

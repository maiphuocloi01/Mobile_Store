package com.groupone.mobilestore.view.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.groupone.mobilestore.databinding.FragmentResetPasswordBinding;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.viewmodel.AccountViewModel;


public class ResetPasswordFragment extends BaseFragment<FragmentResetPasswordBinding, AccountViewModel> {

    public static final String TAG = ResetPasswordFragment.class.getName();

    private Object mData;

    @Override
    protected Class<AccountViewModel> getClassVM() {
        return AccountViewModel.class;
    }

    @Override
    protected void initViews() {

        String email = (String) mData;

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.etPassword.getText())) {
                    binding.etPassword.setError("Vui lòng nhập mật khẩu");
                } else if (TextUtils.isEmpty(binding.etPasswordConfirm.getText())) {
                    binding.etPasswordConfirm.setError("Vui lòng xác nhận mật khẩu");
                } else if (!binding.etPassword.getText().toString().equals(binding.etPasswordConfirm.getText().toString())) {
                    Toast.makeText(context, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    DialogUtils.showLoadingDialog(context);
                    Log.d(TAG, email + " " + binding.etPassword.getText().toString());
                    viewModel.resetPassword(email, binding.etPassword.getText().toString());
                }
            }
        });
    }

    @Override
    protected FragmentResetPasswordBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentResetPasswordBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_RESET_PASSWORD)) {
            Boolean isSuccess = (Boolean) data;
            Log.d(TAG, isSuccess.toString());
            if (isSuccess) {
                Toast.makeText(context, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
            }
            DialogUtils.hideLoadingDialog();
            callBack.showFragment(LoginFragment.TAG, null, false);
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        DialogUtils.hideLoadingDialog();
        Toast.makeText(context, "Error " + code + data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }
}

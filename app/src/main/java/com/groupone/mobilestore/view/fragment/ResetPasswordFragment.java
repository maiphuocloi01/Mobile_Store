package com.groupone.mobilestore.view.fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.databinding.FragmentResetPasswordBinding;
import com.groupone.mobilestore.viewmodel.AccountViewModel;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class ResetPasswordFragment extends BaseFragment<FragmentResetPasswordBinding, AccountViewModel>{

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
                if(TextUtils.isEmpty(binding.etPassword.getText())){
                    binding.etPassword.setError("Vui lòng nhập mật khẩu");
                } else if(TextUtils.isEmpty(binding.etPasswordConfirm.getText())){
                    binding.etPasswordConfirm.setError("Vui lòng xác nhận mật khẩu");
                } else if(!binding.etPassword.getText().toString().equals(binding.etPasswordConfirm.getText().toString())){
                    Toast.makeText(context, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else {

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

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }
}

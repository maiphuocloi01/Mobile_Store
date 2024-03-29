package com.groupone.mobilestore.view.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentSignupBinding;
import com.groupone.mobilestore.model.Token;
import com.groupone.mobilestore.util.CommonUtils;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.viewmodel.AccountViewModel;

import java.util.ArrayList;
import java.util.List;

public class SignupFragment extends BaseFragment<FragmentSignupBinding, AccountViewModel> {

    public static final String TAG = SignupFragment.class.getName();

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


        binding.btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(binding.etEmail.getText())){
                    binding.etEmail.setError("Vui lòng nhập email");
                } else if(!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.getText()).matches()){
                    binding.etEmail.setError("Vui lòng nhập đúng định dạng email");
                }
                else if(TextUtils.isEmpty(binding.etUsername.getText())){
                    binding.etUsername.setError("Vui lòng nhập tên tài khoản");
                }
                else if(TextUtils.isEmpty(binding.etPassword.getText())){
                    binding.etPassword.setError("Vui lòng nhập mật khẩu");
                }
                else if(TextUtils.isEmpty(binding.etPasswordConfirm.getText())){
                    binding.etPasswordConfirm.setError("Vui lòng xác nhận mật khẩu");
                }
                else if(!binding.etPassword.getText().toString().equals(binding.etPasswordConfirm.getText().toString())){
                    Toast.makeText(context, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }
                else if(!binding.cbAccept.isChecked()){
                    Toast.makeText(context, "Bạn cần chấp nhận điều khoản và điều kiện", Toast.LENGTH_SHORT).show();
                }
                else {
                    viewModel.checkRegistrable(
                            binding.etEmail.getText().toString(),
                            binding.etUsername.getText().toString(),
                            binding.etPassword.getText().toString()
                    );
//                    viewModel.setEmail(binding.etEmail.getText().toString());
//                    viewModel.setUsername(binding.etUsername.getText().toString());
//                    viewModel.setPassword(binding.etUsername.getText().toString());
                    DialogUtils.showLoadingDialog(context);
                }

            }
        });

    }

    @Override
    protected FragmentSignupBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentSignupBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if(key.equals(Constants.KEY_CHECK_REGISTER)){
            int response = (int) data;
            Log.d(TAG, "apiSuccess: " + response);
            DialogUtils.hideLoadingDialog();
            if(response == -1){
                Toast.makeText(context, "Tên đăng nhập hoặc email đã tồn tại", Toast.LENGTH_SHORT).show();
            } else if(response == -2){
                Toast.makeText(context, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            } else if(response == 1){
                List<String> listInfo = new ArrayList<>();
                listInfo.add(binding.etEmail.getText().toString());
                listInfo.add(binding.etUsername.getText().toString());
                listInfo.add(binding.etPassword.getText().toString());
                callBack.showFragment(FillInfoFragment.TAG, listInfo, true);
            }
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {

        DialogUtils.hideLoadingDialog();
        Toast.makeText(context, "Không kết nối được máy chủ", Toast.LENGTH_SHORT).show();
    }
}

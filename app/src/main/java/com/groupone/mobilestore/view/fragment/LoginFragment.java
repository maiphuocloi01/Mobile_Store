package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.viewmodel.AccountViewModel.KEY_LOGIN;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentLoginBinding;
import com.groupone.mobilestore.model.Token;
import com.groupone.mobilestore.util.CommonUtils;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.viewmodel.AccountViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

import kotlin.io.TextStreamsKt;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoginFragment extends BaseFragment<FragmentLoginBinding, AccountViewModel> {

    public static final String TAG = LoginFragment.class.getName();
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String USERNAME = "USERNAME";

    @Override
    protected Class<AccountViewModel> getClassVM() {
        return AccountViewModel.class;
    }

    @Override
    protected void initViews() {
        binding.btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(SignupFragment.TAG, null, true);
            }
        });

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.etUsername.getText())) {
                    binding.etUsername.setError("Vui lòng nhập tên tài khoản");
                } else if (TextUtils.isEmpty(binding.etPassword.getText())) {
                    binding.etPassword.setError("Vui lòng nhập mật khẩu");
                } else {
                    viewModel.login(
                            binding.etUsername.getText().toString(),
                            binding.etPassword.getText().toString()
                    );
                    DialogUtils.showLoadingDialog(context);

//                new Handler().postDelayed(() -> {
//                    progressDialog.dismiss();
//                    callBack.showFragment(PagerFragment.TAG, null, false);
//                }, 1000);
                }
            }
        });

        binding.tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(ForgotPasswordFragment.TAG, null, true);
            }
        });
    }

    @Override
    protected FragmentLoginBinding initViewBinding(@NonNull LayoutInflater inflater,
                                                   @Nullable ViewGroup container) {
        return FragmentLoginBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(KEY_LOGIN)) {
            Token token = (Token) data;
            Log.d(TAG, "apiSuccess: " + token.getAccessToken());
            CommonUtils.getInstance().savePref(ACCESS_TOKEN, token.getAccessToken());
            Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            DialogUtils.hideLoadingDialog();
            CommonUtils.getInstance().savePref(USERNAME, binding.etUsername.getText().toString());
            callBack.showFragment(PagerFragment.TAG, null, false);
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        if (code == 400) {
            ResponseBody res = (ResponseBody) data;
            Gson gson = new Gson();
            Type type = new TypeToken<Token>() {}.getType();
            Token errorResponse = gson.fromJson(res.charStream(),type);
            Toast.makeText(context, errorResponse.getErrorDescription(), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "Không kết nối được máy chủ", Toast.LENGTH_SHORT).show();
        }
        DialogUtils.hideLoadingDialog();
    }
}

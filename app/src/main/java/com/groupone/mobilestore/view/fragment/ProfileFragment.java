package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.view.fragment.LoginFragment.USERNAME;
import static com.groupone.mobilestore.viewmodel.AccountViewModel.KEY_GET_BY_USERNAME;
import static com.groupone.mobilestore.viewmodel.AccountViewModel.KEY_LOGIN;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentProfileBinding;
import com.groupone.mobilestore.model.Token;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.CommonUtils;
import com.groupone.mobilestore.viewmodel.AccountViewModel;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding, AccountViewModel> {

    public static final String TAG = ProfileFragment.class.getName();
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private User user;

    @Override
    protected Class<AccountViewModel> getClassVM() {
        return AccountViewModel.class;
    }

    @Override
    protected void initViews() {

        binding.rowEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rowEditProfile.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                actionShowFragment(EditProfileFragment.TAG, user, true);

            }
        });

        binding.rowShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rowShipment.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                actionShowFragment(AddressFragment.TAG, null, true);
            }
        });

        binding.rowPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rowPayment.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                actionShowFragment(BankFragment.TAG, null, true);
            }
        });

        binding.rowFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rowFavorite.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                actionShowFragment(FavoriteFragment.TAG, null, true);
            }
        });

        binding.rowTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rowTerm.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
            }
        });

        binding.rowVersionInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rowVersionInfo.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                actionShowFragment(IntroduceFragment.TAG, null, true);
            }
        });

        binding.btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btLogout.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                showAlertDialog();
            }
        });

        String username = CommonUtils.getInstance().getPref(USERNAME);
        viewModel.getUserByUserName(username);
    }

    private void actionShowFragment(String tag, Object data, boolean isBack) {
        PagerFragment parentFrag = ((PagerFragment) ProfileFragment.this.getParentFragment());
        if (parentFrag != null) {
            parentFrag.setActionShowFragment(tag, data, isBack);
        }
    }

    private void showAlertDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_alert_dialog);
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

        Button btnCancel = dialog.findViewById(R.id.bt_cancel3);
        Button btnConfirm = dialog.findViewById(R.id.bt_confirm3);

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnConfirm.setOnClickListener(view -> {
            actionShowFragment(LoginFragment.TAG, null, false);
            CommonUtils.getInstance().clearPref(ACCESS_TOKEN);
            dialog.dismiss();
        });


        dialog.show();
    }

    @Override
    protected FragmentProfileBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentProfileBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if(key.equals(KEY_GET_BY_USERNAME)){
            user = (User) data;
            Log.d(TAG, "apiSuccess: " + user.getUserName());
            binding.tvName.setText(user.getFullName());
            binding.tvEmail.setText(user.getEmail());
            Glide.with(context).load(user.getAvatar()).into(binding.ivAvatar);
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        Log.d(TAG, "error: " + code + data);
        Toast.makeText(context, "Error: " + code + ", " + data, Toast.LENGTH_SHORT).show();

    }
}

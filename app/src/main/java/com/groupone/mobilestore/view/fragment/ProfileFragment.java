package com.groupone.mobilestore.view.fragment;


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
import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentProfileBinding;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.CommonUtils;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.viewmodel.AccountViewModel;

import me.samlss.broccoli.Broccoli;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding, AccountViewModel> {

    public static final String TAG = ProfileFragment.class.getName();
    private User user = MyApplication.getInstance().getStorage().user;
    //private Broccoli mBroccoli;

    @Override
    protected Class<AccountViewModel> getClassVM() {
        return AccountViewModel.class;
    }

    @Override
    protected void initViews() {

//        mBroccoli = new Broccoli();
//        mBroccoli.addPlaceholders(binding.ivAvatar, binding.tvName, binding.tvEmail,
//                binding.rowEditProfile, binding.rowFavorite, binding.rowPayment, binding.rowShipment, binding.rowTerm, binding.rowVersionInfo,
//                binding.tvTitle1, binding.tvTitle2, binding.ivIcon1, binding.ivIcon2, binding.ivIcon3, binding.ivIcon4,
//                binding.ivIcon5, binding.ivIcon6, binding.ivNext1, binding.ivNext2, binding.ivNext3, binding.ivNext4, binding.ivNext5, binding.ivNext6,
//                binding.tvFunc1, binding.tvFunc2, binding.tvFunc3, binding.tvFunc4, binding.tvFunc5, binding.tvFunc6, binding.btLogout
//        );
//        mBroccoli.show();

        if(user != null) {
            binding.tvName.setText(user.getFullName());
            binding.tvEmail.setText(user.getEmail());
            Glide.with(context).load(user.getAvatar()).into(binding.ivAvatar);
        }

        binding.rowEditProfile.setOnClickListener(view -> {
            binding.rowEditProfile.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
            if (user != null) {
                actionShowFragment(EditProfileFragment.TAG, user, true);
            }
        });

        binding.rowShipment.setOnClickListener(view -> {
            binding.rowShipment.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
            actionShowFragment(AddressFragment.TAG, null, true);
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

        //String username = CommonUtils.getInstance().getPref(Constants.USERNAME);
        //viewModel.getUserByUserName(username);
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
            CommonUtils.getInstance().clearPref(Constants.ACCESS_TOKEN);
            CommonUtils.getInstance().clearPref(Constants.USERNAME);
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
//        if (key.equals(Constants.KEY_GET_BY_USERNAME)) {
//            user = (User) data;
//            Log.d(TAG, "apiSuccess: " + user.getUserName());
//            //mBroccoli.clearAllPlaceholders();
//
//        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        Log.d(TAG, "error: " + code + data);
        Toast.makeText(context, "Error: " + code + ", " + data, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResume() {
        super.onResume();
        user = MyApplication.getInstance().getStorage().user;
        //Log.d(TAG, "onResume: " + MyApplication.getInstance().getStorage().user.getFullName());
        if(user != null) {
            binding.tvName.setText(user.getFullName());
            binding.tvEmail.setText(user.getEmail());
            Glide.with(context).load(user.getAvatar()).into(binding.ivAvatar);
        }
    }
}

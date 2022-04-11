package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.groupone.mobilestore.databinding.FragmentProfileBinding;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding, CommonViewModel> {

    public static final String TAG = ProfileFragment.class.getName();

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

        binding.rowEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rowEditProfile.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
            }
        });

        binding.rowShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rowShipment.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
            }
        });

        binding.rowPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rowPayment.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
            }
        });

        binding.rowFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rowFavorite.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
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
            }
        });

        binding.btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.btLogout.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
                PagerFragment parentFrag = ((PagerFragment)ProfileFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(LoginFragment.TAG, null, false);
                }
            }
        });
    }

    @Override
    protected FragmentProfileBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentProfileBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}

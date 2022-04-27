package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.databinding.FragmentResetPasswordBinding;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class ResetPasswordFragment extends BaseFragment<FragmentResetPasswordBinding, CommonViewModel>{

    public static final String TAG = ResetPasswordFragment.class.getName();

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected FragmentResetPasswordBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentResetPasswordBinding.inflate(inflater, container, false);
    }
}

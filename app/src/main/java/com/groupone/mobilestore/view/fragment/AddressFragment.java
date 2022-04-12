package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.databinding.FragmentAddressBinding;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class AddressFragment extends BaseFragment<FragmentAddressBinding, CommonViewModel>{

    public static final String TAG = AddressFragment.class.getName();

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected FragmentAddressBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentAddressBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}
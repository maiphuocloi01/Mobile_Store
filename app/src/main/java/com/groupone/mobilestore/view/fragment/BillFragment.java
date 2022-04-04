package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.groupone.mobilestore.databinding.FragmentBillBinding;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class BillFragment extends BaseFragment<FragmentBillBinding, CommonViewModel> {

    public static final String TAG = BillFragment.class.getName();

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected FragmentBillBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentBillBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}

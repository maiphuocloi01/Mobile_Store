package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.databinding.FragmentOrderDetailBinding;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class OrderDetailFragment extends BaseFragment<FragmentOrderDetailBinding, CommonViewModel>{

    public static final String TAG = OrderDetailFragment.class.getName();

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });
        binding.btDoAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(EvaluateFragment.TAG, null, true);
            }
        });
    }

    @Override
    protected FragmentOrderDetailBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentOrderDetailBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}
package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.databinding.FragmentEvaluateBinding;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class EvaluateFragment extends BaseFragment<FragmentEvaluateBinding, CommonViewModel>{

    public static final String TAG = EvaluateFragment.class.getName();

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
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        binding.btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(PagerFragment.TAG, null, false);
            }
        });
    }

    @Override
    protected FragmentEvaluateBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentEvaluateBinding.inflate(inflater, container, false);
    }
}

package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.groupone.mobilestore.databinding.FragmentEvaluateBinding;
import com.groupone.mobilestore.model.Order;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class EvaluateFragment extends BaseFragment<FragmentEvaluateBinding, CommonViewModel>{

    public static final String TAG = EvaluateFragment.class.getName();

    private Object mData;

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

        Order order = (Order) mData;

        binding.tvProductName.setText(order.getProductName());
        binding.tvType.setText(order.getType());
        Glide.with(context).load(order.getImage()).into(binding.ivProduct);

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

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }
}

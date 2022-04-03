package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.groupone.mobilestore.databinding.FragmentHomeBinding;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, CommonViewModel> {

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                //callBack.showFragment(LoginFragment.TAG, null, true);
                //callBack.backToPrev();
                PagerFragment parentFrag = ((PagerFragment)HomeFragment.this.getParentFragment());
                parentFrag.setActionShowFragment(LoginFragment.TAG, null, true);
            }
        });

    }

    @Override
    protected FragmentHomeBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}

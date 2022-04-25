package com.groupone.mobilestore.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.databinding.FragmentSearchResultBinding;
import com.groupone.mobilestore.view.adapter.FilterAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.List;

public class SearchResultFragment extends BaseFragment<FragmentSearchResultBinding, CommonViewModel> {

    public static final String TAG = SearchResultFragment.class.getName();
    private Object mData;

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
        Bundle newData = (Bundle) mData;
        if (newData != null) {
            //List<String> listFilter = (List<String>) mData;
            List<String> listFilter = newData.getStringArrayList("filter");
//        for(int i = 0; i<listFilter.size(); i++){
//            Log.d(TAG, listFilter.get(i));
//        }

            binding.rvFilter.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            FilterAdapter adapter = new FilterAdapter(context, listFilter);
            binding.rvFilter.setAdapter(adapter);
        }

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(PagerFragment.TAG, null, false);
            }
        });

        binding.ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(FilterFragment.TAG, null, true);
            }
        });


    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }

    @Override
    protected FragmentSearchResultBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentSearchResultBinding.inflate(inflater, container, false);
    }
}

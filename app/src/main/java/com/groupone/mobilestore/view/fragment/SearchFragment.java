package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.IMEUtils.hideSoftInput;
import static com.groupone.mobilestore.util.IMEUtils.showSoftInput;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.databinding.FragmentSearchBinding;
import com.groupone.mobilestore.view.adapter.RecommendAdapter;
import com.groupone.mobilestore.view.adapter.VersionAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseFragment<FragmentSearchBinding, CommonViewModel> implements RecommendAdapter.RecommendCallBack {

    public static final String TAG = SearchFragment.class.getName();

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

        List<String> listRecommend = new ArrayList<>();
        listRecommend.add("iPhone 13 Pro Max");
        listRecommend.add("Xiaomi 11T");
        listRecommend.add("Samsung Galaxy S22 Ultra");

        binding.rvRecommend.setLayoutManager(new LinearLayoutManager(context));
        RecommendAdapter adapterRecommend = new RecommendAdapter(context, listRecommend, this);
        binding.rvRecommend.setAdapter(adapterRecommend);


        if (binding.etSearch.requestFocus()) {
            showSoftInput(binding.etSearch);
        }

//        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    //performSearch();
//
//                    return true;
//                }
//                return false;
//            }
//        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        binding.ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(FilterFragment.TAG, null, true);
            }
        });

        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(!v.getText().toString().trim().equals("")) {
                        hideSoftInput(binding.etSearch);
                        Bundle bundle = new Bundle();
                        bundle.putString("search", binding.etSearch.getText().toString().trim());
                        callBack.showFragment(SearchResultFragment.TAG, bundle, false);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected FragmentSearchBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentSearchBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void gotoSearch(String searchText) {
        Bundle bundle = new Bundle();
        bundle.putString("search", searchText);
        callBack.showFragment(SearchResultFragment.TAG, bundle, false);
    }
}

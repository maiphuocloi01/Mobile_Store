package com.groupone.mobilestore.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.databinding.FragmentSearchResultBinding;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.StringConvert;
import com.groupone.mobilestore.view.adapter.FilterAdapter;
import com.groupone.mobilestore.view.adapter.ProductAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;
import com.groupone.mobilestore.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends BaseFragment<FragmentSearchResultBinding, HomeViewModel> {

    public static final String TAG = SearchResultFragment.class.getName();
    private Object mData;
    private String textSearch = "";
    private List<Product> listProduct = new ArrayList<>();


    @Override
    protected Class<HomeViewModel> getClassVM() {
        return HomeViewModel.class;
    }

    @Override
    protected void initViews() {

        viewModel.getTopSaleProduct();
        Bundle newData = (Bundle) mData;
        if (newData != null) {
            //List<String> listFilter = (List<String>) mData;
            List<String> listFilter = new ArrayList<>();
            if (newData.getStringArrayList("filter") != null) {
                listFilter = newData.getStringArrayList("filter");
            }

            textSearch = newData.getString("search");
            Log.d(TAG, "initViews: " + textSearch);
//        for(int i = 0; i<listFilter.size(); i++){
//            Log.d(TAG, listFilter.get(i));
//        }
            binding.etSearch.setText(textSearch);
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

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_GET_TOP_SALE_PRODUCT)) {
            //Log.d(TAG, "apiSuccess: " + data.toString());
            List<Product> products = (List<Product>) data;
            //listProduct = products;
            List<Product> productSearch = new ArrayList<>();
            if(products.size() > 0 && textSearch != null){
                for (Product item: products){
                    if(StringConvert.removeDiacriticalMarks(item.getName()).toLowerCase().contains(textSearch.toLowerCase())){
                        productSearch.add(item);
                    }
                }
            }
            binding.rvProduct.setLayoutManager(new GridLayoutManager(context, 2));
            ProductAdapter adapter = new ProductAdapter(context, productSearch);
            binding.rvProduct.setAdapter(adapter);
            //binding.rvProduct.setFocusable(false);
            //binding.rvProduct.setNestedScrollingEnabled(false);

            adapter.getProductLD().observe(this, new Observer<Product>() {
                @Override
                public void onChanged(Product product) {
//                    parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
//                    if (parentFrag != null) {
//                        parentFrag.setActionShowFragment(ProductFragment.TAG, product, true);
//                    }
                    callBack.showFragment(ProductFragment.TAG, product, true);
                }
            });
        }


    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}

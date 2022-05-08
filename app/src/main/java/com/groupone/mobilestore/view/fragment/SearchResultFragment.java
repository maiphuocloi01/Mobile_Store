package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.IMEUtils.hideSoftInput;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentSearchResultBinding;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.StringConvert;
import com.groupone.mobilestore.util.ViewUtils;
import com.groupone.mobilestore.view.adapter.FilterAdapter;
import com.groupone.mobilestore.view.adapter.ProductAdapter;
import com.groupone.mobilestore.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchResultFragment extends BaseFragment<FragmentSearchResultBinding, HomeViewModel> {

    public static final String TAG = SearchResultFragment.class.getName();
    private Object mData;
    private List<Product> listProduct = MyApplication.getInstance().getStorage().listProduct;

    @Override
    protected Class<HomeViewModel> getClassVM() {
        return HomeViewModel.class;
    }

    @Override
    protected void initViews() {

        if (listProduct == null) {
            viewModel.getTopSaleProduct();
        }
        Bundle newData = (Bundle) mData;
        if (newData != null) {
            //List<String> listFilter = (List<String>) mData;
            List<String> listFilter = new ArrayList<>();
            if (newData.getStringArrayList("filter") != null) {
                listFilter = newData.getStringArrayList("filter");
            }
            binding.etSearch.setText(newData.getString("search"));
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

        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideSoftInput(binding.etSearch);
                    Log.d(TAG, "onEditorAction: " + listProduct.size());
                    searchProduct(v.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        searchProduct(binding.etSearch.getText().toString().trim());
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
            listProduct = (List<Product>) data;
            MyApplication.getInstance().getStorage().listProduct = listProduct;
        }
    }

    private void searchProduct(String strSearch) {
        List<Product> productSearch = new ArrayList<>();
        Log.d(TAG, "searchProduct: " + strSearch);
        if (listProduct.size() > 0 && !strSearch.equals("")) {
            for (Product item : listProduct) {
                if (StringConvert.removeDiacriticalMarks(item.getName()).toLowerCase().contains(strSearch.toLowerCase())) {
                    productSearch.add(item);
                }
            }

        }
        if (productSearch.size() > 0) {
            Log.d(TAG, "productSearch: " + productSearch.size());
            ViewUtils.show(binding.tvCountResult);
            ViewUtils.gone(binding.layoutEmptySearch);
            binding.tvCountResult.setText("Tìm thấy " + productSearch.size() + " kết quả");
            binding.rvProduct.setLayoutManager(new GridLayoutManager(context, 2));
            ProductAdapter adapter = new ProductAdapter(context, productSearch);
            binding.rvProduct.setAdapter(adapter);
            adapter.getProductLD().observe(this, new Observer<Product>() {
                @Override
                public void onChanged(Product product) {
                    callBack.showFragment(ProductFragment.TAG, product, true);
                }
            });
        } else {
            ViewUtils.gone(binding.tvCountResult);
            ViewUtils.show(binding.layoutEmptySearch);
        }

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}

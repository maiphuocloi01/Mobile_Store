package com.groupone.mobilestore.view.fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentHomeBinding;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.view.adapter.ProductAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, CommonViewModel> {

    public static final String TAG = HomeFragment.class.getName();


    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {
//        binding.btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PagerFragment parentFrag = ((PagerFragment)HomeFragment.this.getParentFragment());
//                parentFrag.setActionShowFragment(LoginFragment.TAG, null, true);
//            }
//        });

        List<Product> listProduct = new ArrayList<>();

        listProduct.add(new Product(1, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(2, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(3, R.drawable.img_iphone13, "iPhone 13 Pro Max Ultra Ultimate Super Plus", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(4, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(5, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(6, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));

        binding.rvProduct.setLayoutManager(new GridLayoutManager(context, 2));
        ProductAdapter adapter = new ProductAdapter(context, listProduct);
        binding.rvProduct.setAdapter(adapter);

        binding.rowSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PagerFragment parentFrag = ((PagerFragment) HomeFragment.this.getParentFragment());
                if (parentFrag != null) {
                    parentFrag.setActionShowFragment(SearchFragment.TAG, null, true);
                }
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

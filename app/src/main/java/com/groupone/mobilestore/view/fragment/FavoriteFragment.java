package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentFavoriteBinding;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.view.adapter.ProductAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends BaseFragment<FragmentFavoriteBinding, CommonViewModel>{

    public static final String TAG = FavoriteFragment.class.getName();

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

        List<Product> listProduct = new ArrayList<>();

        listProduct.add(new Product(1, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(2, R.drawable.img_iphone13_2, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(3, R.drawable.img_iphone13_3, "iPhone 13 Pro Max Ultra Ultimate Super Plus", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(4, R.drawable.img_iphone13_4, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(5, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));
        listProduct.add(new Product(6, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", 3.8, 34000000, 38));

        binding.rvFavor.setLayoutManager(new LinearLayoutManager(context));
        ProductAdapter adapter = new ProductAdapter(context, listProduct);
        binding.rvFavor.setAdapter(adapter);

        binding.tvCount.setText(listProduct.size() + " sản phẩm");

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

    }

    @Override
    protected FragmentFavoriteBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFavoriteBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}

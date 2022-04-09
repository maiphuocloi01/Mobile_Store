package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentCartBinding;
import com.groupone.mobilestore.model.Cart;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.view.adapter.CartAdapter;
import com.groupone.mobilestore.view.adapter.ProductAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends BaseFragment<FragmentCartBinding, CommonViewModel>{

    public static final String TAG = CartFragment.class.getName();

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {
        List<Cart> listCart = new ArrayList<>();

        listCart.add(new Cart(1, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", "Xám", 34000000, 1));
        listCart.add(new Cart(1, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", "Xám", 34000000, 1));
        listCart.add(new Cart(1, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", "Xám", 34000000, 1));
        listCart.add(new Cart(1, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", "Xám", 34000000, 1));


        binding.rvCart.setLayoutManager(new LinearLayoutManager(context));
        CartAdapter adapter = new CartAdapter(context, listCart);
        binding.rvCart.setAdapter(adapter);
    }

    @Override
    protected FragmentCartBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentCartBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}

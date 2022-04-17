package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.NumberUtils.convertParentheses;
import static com.groupone.mobilestore.util.NumberUtils.convertPrice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentCartBinding;
import com.groupone.mobilestore.model.Cart;
import com.groupone.mobilestore.view.adapter.CartAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends BaseFragment<FragmentCartBinding, CommonViewModel> {

    public static final String TAG = CartFragment.class.getName();
    List<Cart> listCart;

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {
        listCart = new ArrayList<>();

        listCart.add(new Cart(1, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", "Xám", 34000000, 1));
        listCart.add(new Cart(1, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", "Xám", 34000000, 1));
        listCart.add(new Cart(1, R.drawable.img_iphone13, "iPhone 13 Pro Max", "128GB", "Xám", 34000000, 1));
        listCart.add(new Cart(1, R.drawable.img_iphone13, "iPhone 13 Pro Max Ultra Ultimate Super Plus", "128GB", "Xám", 34000000, 1));


        binding.rvCart.setLayoutManager(new LinearLayoutManager(context));
        CartAdapter adapter = new CartAdapter(context, listCart);
        binding.rvCart.setAdapter(adapter);

        binding.tvTotalCount.setText(convertParentheses(listCart.size()));

        adapter.getListCartLD().observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                if (carts.isEmpty()) {
                    binding.tvTotalCount.setText(convertParentheses(0));
                    binding.tvProductCost.setText(convertPrice(0));
                    binding.tvShipCost.setText(convertPrice(0));
                    binding.tvTotalCost.setText(convertPrice(0));
                    binding.btPayment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(context, "Không có sản phẩm nào trong giỏ hàng!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
                showPayment(carts);
            }
        });



    }

    private void showPayment(List<Cart> carts) {
        long productCost = 0L;
        long shipCost = 0L;
        for (Cart cart : carts) {
            if (cart.isSelected()) {
                productCost += cart.getPrice() * cart.getQty();
            }
        }
        if (productCost > 0) {
            shipCost = 20000L;
        }
        binding.btPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Cart item: carts){
                    if(item.isSelected()){
                        actionShowFragment(PaymentFragment.TAG, null, true);
                    }
                }
                Toast.makeText(context, "Chưa có sản phẩm nào được chọn!", Toast.LENGTH_SHORT).show();
            }
        });
        binding.tvTotalCount.setText(convertParentheses(listCart.size()));
        binding.tvProductCost.setText(convertPrice(productCost));
        binding.tvShipCost.setText(convertPrice(shipCost));
        binding.tvTotalCost.setText(convertPrice(productCost + shipCost));
    }

    private void actionShowFragment(String tag, Object data, boolean isBack) {
        PagerFragment parentFrag = ((PagerFragment) CartFragment.this.getParentFragment());
        if (parentFrag != null) {
            parentFrag.setActionShowFragment(tag, data, isBack);
        }
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

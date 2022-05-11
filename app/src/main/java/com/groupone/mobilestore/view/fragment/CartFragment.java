package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.NumberUtils.convertParentheses;
import static com.groupone.mobilestore.util.NumberUtils.convertPrice;
import static com.groupone.mobilestore.util.ViewUtils.gone;
import static com.groupone.mobilestore.util.ViewUtils.show;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentCartBinding;
import com.groupone.mobilestore.model.Cart;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.view.adapter.CartAdapter;
import com.groupone.mobilestore.view.adapter.ProductAdapter;
import com.groupone.mobilestore.viewmodel.CartViewModel;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends BaseFragment<FragmentCartBinding, CartViewModel> {

    public static final String TAG = CartFragment.class.getName();

    private final User user = MyApplication.getInstance().getStorage().user;

    @Override
    protected Class<CartViewModel> getClassVM() {
        return CartViewModel.class;
    }

    @Override
    protected void initViews() {

        binding.rvCart.setLayoutManager(new LinearLayoutManager(context));
        if(MyApplication.getInstance().getStorage().listCart == null){
            Log.d(TAG, "initViews: " + user.getId());
            viewModel.getShoppingCartByAccountId(user.getId());
        }
        else{
            Log.d(TAG, "get data storage ");
            List<ShoppingCart> listCart = MyApplication.getInstance().getStorage().listCart;
            initShoppingCart(listCart);
        }


    }

    private void initShoppingCart(List<ShoppingCart> shoppingCartList){
        CartAdapter cartAdapter = new CartAdapter(context, shoppingCartList);
        binding.rvCart.setAdapter(cartAdapter);
        binding.tvTotalCount.setText(convertParentheses(shoppingCartList.size()));
        cartAdapter.getListCartLD().observe(this, new Observer<List<ShoppingCart>>() {
            @Override
            public void onChanged(List<ShoppingCart> carts) {
                if (carts.isEmpty()) {
                    binding.tvTotalCount.setText(convertParentheses(0));
                    binding.tvProductCost.setText(convertPrice(0));
                    binding.tvShipCost.setText(convertPrice(0));
                    binding.tvTotalCost.setText(convertPrice(0));
                    gone(binding.layoutCart);
                    show(binding.layoutEmptyCart);
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

    private void showPayment(List<ShoppingCart> carts) {
        long productCost = 0L;
        long shipCost = 0L;
        for (ShoppingCart cart : carts) {
            if (cart.isSelected()) {
                productCost += cart.getPrice() * cart.getQuantity();
            }
        }
        if (productCost > 0) {
            shipCost = 20000L;
        }
        binding.btPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(ShoppingCart item: carts){
                    if(item.isSelected()){
                        actionShowFragment(PaymentFragment.TAG, null, true);
                    }
                }
            }
        });
        binding.tvTotalCount.setText(convertParentheses(carts.size()));
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

        if(key.equals(Constants.KEY_GET_SHOPPING_CART_BY_ACCOUNT)){
            Log.d(TAG, "apiSuccess: " + data.toString());
            List<ShoppingCart> cartList = (List<ShoppingCart>) data;

            MyApplication.getInstance().getStorage().listCart = cartList;

            initShoppingCart(cartList);
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        if(key.equals(Constants.KEY_GET_SHOPPING_CART_BY_ACCOUNT)){
            if(code == 999) {
                Log.d(TAG, "apiError: "+ data.toString());
                Toast.makeText(context, "Không thể kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.NumberUtils.convertParentheses;
import static com.groupone.mobilestore.util.NumberUtils.convertPrice;
import static com.groupone.mobilestore.util.ViewUtils.gone;
import static com.groupone.mobilestore.util.ViewUtils.show;

import android.os.Bundle;
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
import com.groupone.mobilestore.databinding.FragmentCartBinding;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.view.adapter.CartAdapter;
import com.groupone.mobilestore.viewmodel.CartViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends BaseFragment<FragmentCartBinding, CartViewModel> implements CartAdapter.CartCallBack {

    public static final String TAG = CartFragment.class.getName();

    private final User user = MyApplication.getInstance().getStorage().user;
    private List<ShoppingCart> cartList;
    private List<ShoppingCart> chooseCart = new ArrayList<>();

    @Override
    protected Class<CartViewModel> getClassVM() {
        return CartViewModel.class;
    }

    @Override
    protected void initViews() {

        chooseCart.clear();
        binding.rvCart.setLayoutManager(new LinearLayoutManager(context));
        if (MyApplication.getInstance().getStorage().listCart == null) {
            Log.d(TAG, "initViews: " + user.getId());
            viewModel.getShoppingCartByAccountId(user.getId());
        } else {
            Log.d(TAG, "get data storage ");
            cartList = MyApplication.getInstance().getStorage().listCart;
            if (cartList.size() != 0) {
                removeSelected(cartList);
                initShoppingCart(cartList);
            } else {
                gone(binding.layoutCart);
                show(binding.layoutEmptyCart);
            }
        }

        binding.btPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                for (ShoppingCart item : carts) {
//                    if (item.isSelected()) {
//                        actionShowFragment(PaymentFragment.TAG, null, true);
//                    }
//                }
                if (chooseCart.size() > 0){
                    Bundle bundle = new Bundle();
                    ListCart carts = new ListCart();
                    carts.cartList = chooseCart;
                    bundle.putSerializable("carts", carts);
                    actionShowFragment(PaymentFragment.TAG, bundle, true);
                } else {
                    Log.d(TAG, "onClick: ");
                    Toast.makeText(context, "Bạn chưa chọn sản phẩm nào", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void initShoppingCart(List<ShoppingCart> shoppingCartList) {
        CartAdapter cartAdapter = new CartAdapter(context, shoppingCartList, this);
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
                }
                showPayment(carts);
            }
        });
    }

    private void showPayment(List<ShoppingCart> carts) {
        chooseCart.clear();
        long productCost = 0L;
        long shipCost = 0L;
        for (ShoppingCart cart : carts) {
            if (cart.isSelected()) {
                chooseCart.add(cart);
                productCost += cart.getPrice() * cart.getQuantity();
            }
        }
        if (productCost > 0) {
            shipCost = 20000L;
        }
        binding.tvTotalCount.setText(convertParentheses(carts.size()));
        binding.tvProductCost.setText(convertPrice(productCost));
        binding.tvShipCost.setText(convertPrice(shipCost));
        binding.tvTotalCost.setText(convertPrice(productCost + shipCost));
    }

    private void removeSelected(List<ShoppingCart> carts) {
        for (ShoppingCart cart : carts) {
            cart.setSelected(false);
        }
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

        if (key.equals(Constants.KEY_GET_SHOPPING_CART_BY_ACCOUNT)) {
            Log.d(TAG, "apiSuccess: " + data.toString());
            cartList = (List<ShoppingCart>) data;

            MyApplication.getInstance().getStorage().listCart = cartList;
            if (cartList.size() != 0) {
                removeSelected(cartList);
                initShoppingCart(cartList);
            } else {
                gone(binding.layoutCart);
                show(binding.layoutEmptyCart);
            }

        } else if (key.equals(Constants.KEY_DELETE_CART)) {
            if ((boolean) data) {
                Log.d(TAG, "KEY_DELETE_CART: thành công");
            } else {
                Log.d(TAG, "KEY_DELETE_CART: thất bại");
            }
        } else if (key.equals(Constants.KEY_UPDATE_QUANTITY)) {
            if ((boolean) data) {
                Log.d(TAG, "KEY_UPDATE_QUANTITY: thành công");
            } else {
                Log.d(TAG, "KEY_DELETE_CART: thất bại");
            }
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        if (code == 999) {
            Log.d(TAG, "apiError: " + data.toString());
            Toast.makeText(context, "Không thể kết nối đến máy chủ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateQuantity(int id, int quantity) {
        viewModel.updateQuantity(id, quantity);
    }

    @Override
    public void deleteItemCart(int id) {
        viewModel.deleteItemCartById(id);
    }

    public static class ListCart implements Serializable{
        public List<ShoppingCart> cartList;
    }
}

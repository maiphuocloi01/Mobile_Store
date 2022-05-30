package com.groupone.mobilestore.view.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentDeliveredBinding;
import com.groupone.mobilestore.model.Order;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.util.ViewUtils;
import com.groupone.mobilestore.view.adapter.AddressAdapter;
import com.groupone.mobilestore.view.adapter.OrderAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;
import com.groupone.mobilestore.viewmodel.OrderViewModel;

import java.util.ArrayList;
import java.util.List;

public class DeliveredFragment extends BaseFragment<FragmentDeliveredBinding, OrderViewModel> implements OrderAdapter.OderCallback {

    public static final String TAG = DeliveredFragment.class.getName();

    private final User user = MyApplication.getInstance().getStorage().user;
    private static final int EVALUATE_BILL = 2;
    private List<Order> orderList = new ArrayList<>();

    @Override
    protected Class<OrderViewModel> getClassVM() {
        return OrderViewModel.class;
    }

    @Override
    protected void initViews() {

        Log.d(TAG, "initViews: ");
//        if (MyApplication.getInstance().getStorage().listOrder == null) {
//            viewModel.getBillAccountId(user.getId());
//        } else {
//            orderList = MyApplication.getInstance().getStorage().listOrder;
//            initOrderView();
//        }

    }

    @Override
    protected FragmentDeliveredBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentDeliveredBinding.inflate(inflater, container, false);
    }

    private void initOrderView() {
        List<Order> listOrder = new ArrayList<>();
        for (Order item : orderList) {
            if (item.getStatus() != 0) {
                listOrder.add(item);
            }
        }
        if (listOrder.size() > 0) {
            ViewUtils.gone(binding.layoutEmpty);
            ViewUtils.show(binding.frameRv);
            binding.rvDelivered.setLayoutManager(new LinearLayoutManager(context));
            OrderAdapter adapter = new OrderAdapter(context, listOrder, this);
            binding.rvDelivered.setAdapter(adapter);
        } else {
            ViewUtils.show(binding.layoutEmpty);
            ViewUtils.gone(binding.frameRv);
        }
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_GET_BILL)) {
            orderList = (List<Order>) data;
            MyApplication.getInstance().getStorage().listOrder = orderList;
            initOrderView();

        } else if (key.equals(Constants.KEY_ADD_SHOPPING_CART)){
            int response = (int) data;
            if(response == -1){
                Toast.makeText(context, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                viewModel.getShoppingCartByAccountId(user.getId());
            }
        } else if (key.equals(Constants.KEY_GET_SHOPPING_CART_BY_ACCOUNT)){
            MyApplication.getInstance().getStorage().listCart = (List<ShoppingCart>) data;
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
    public void gotoReview(Order order) {
        OrderFragment parentFrag = ((OrderFragment) DeliveredFragment.this.getParentFragment());
        if (parentFrag != null) {
            parentFrag.setActionShowFragmentFromPager(EvaluateFragment.TAG, order, true);
        }
    }

    @Override
    public void gotoRepurchase(Order order) {
//        OrderFragment parentFrag = ((OrderFragment) DeliveredFragment.this.getParentFragment());
//        if (parentFrag != null) {
//            parentFrag.setActionShowFragmentFromPager(PaymentFragment.TAG, null, true);
//        }

        ShoppingCart cart = new ShoppingCart(user.getId(), order.getProductId(), order.getTotalPrice()/order.getQuantity(),   order.getType());
        viewModel.addShoppingCart(cart);
    }

    @Override
    public void gotoDetail(Order order) {
        OrderFragment parentFrag = ((OrderFragment) DeliveredFragment.this.getParentFragment());
        if (parentFrag != null) {
            parentFrag.setActionShowFragmentFromPager(OrderDetailFragment.TAG, order, true);
        }
    }

    @Override
    public void cancelOrder(int id) {

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        if (MyApplication.getInstance().getStorage().listOrder == null) {
            viewModel.getBillAccountId(user.getId());
        } else {
            orderList = MyApplication.getInstance().getStorage().listOrder;
            initOrderView();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }
}

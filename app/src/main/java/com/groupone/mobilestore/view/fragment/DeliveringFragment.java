package com.groupone.mobilestore.view.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentDeliveringBinding;
import com.groupone.mobilestore.model.Order;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.ViewUtils;
import com.groupone.mobilestore.view.adapter.OrderAdapter;
import com.groupone.mobilestore.viewmodel.OrderViewModel;

import java.util.ArrayList;
import java.util.List;

public class DeliveringFragment extends BaseFragment<FragmentDeliveringBinding, OrderViewModel> implements OrderAdapter.OderCallback {

    public static final String TAG = DeliveringFragment.class.getName();
    private static final int CANCEL_BILL = 3;
    private final User user = MyApplication.getInstance().getStorage().user;
    private List<Order> orderList = new ArrayList<>();

    @Override
    protected Class<OrderViewModel> getClassVM() {
        return OrderViewModel.class;
    }

    @Override
    protected void initViews() {

        if (MyApplication.getInstance().getStorage().listOrder == null) {
            viewModel.getBillAccountId(user.getId());
        } else {
            orderList = MyApplication.getInstance().getStorage().listOrder;
            initOrderView();
        }
    }

    @Override
    protected FragmentDeliveringBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentDeliveringBinding.inflate(inflater, container, false);
    }

    private void initOrderView() {
        List<Order> listOrder = new ArrayList<>();
        for (Order item : orderList) {
            if (item.getStatus() == 0) {
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
        } else if (key.equals(Constants.KEY_UPDATE_BILL)) {
            boolean response = (boolean) data;
            if (response) {
                Log.d(TAG, "apiSuccess: thành công");
                viewModel.getBillAccountId(user.getId());
            } else {
                Log.d(TAG, "apiSuccess: thất bại");
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

    private void cancelOrderDialog(int id, int status) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_cancel_order);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        dialog.setCancelable(false);

        //Ánh xạ view
        EditText etReason = dialog.findViewById(R.id.et_reason);
        Button btnCancel = dialog.findViewById(R.id.bt_cancel4);
        Button btnConfirm = dialog.findViewById(R.id.bt_confirm4);


        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnConfirm.setOnClickListener(view -> {
            if (TextUtils.isEmpty(etReason.getText())) {
                etReason.setError("Không được bỏ trống");
            } else {
                Order order = new Order(id, status, etReason.getText().toString().trim());
                viewModel.updateBill(order);
                dialog.dismiss();
            }

        });

        dialog.show();
    }


    @Override
    public void gotoReview(Order order) {

    }

    @Override
    public void gotoRepurchase(Order order) {

    }

    @Override
    public void gotoDetail(Order order) {
        OrderFragment parentFrag = ((OrderFragment) DeliveringFragment.this.getParentFragment());
        if (parentFrag != null) {
            parentFrag.setActionShowFragmentFromPager(OrderDetailFragment.TAG, order, true);
        }
    }

    @Override
    public void cancelOrder(int id) {
        cancelOrderDialog(id, CANCEL_BILL);
    }
}

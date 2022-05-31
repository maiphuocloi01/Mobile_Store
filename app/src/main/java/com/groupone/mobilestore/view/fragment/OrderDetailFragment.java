package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.NumberUtils.convertPrice;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentOrderDetailBinding;
import com.groupone.mobilestore.model.Order;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.viewmodel.OrderViewModel;

import java.util.List;

public class OrderDetailFragment extends BaseFragment<FragmentOrderDetailBinding, OrderViewModel> {

    public static final String TAG = OrderDetailFragment.class.getName();
    private static final int EVALUATE_BILL = 2;
    private static final int CANCEL_BILL = 3;
    private final User user = MyApplication.getInstance().getStorage().user;
    private Object mData;
    private List<Product> productList = MyApplication.getInstance().getStorage().listProduct;

    @Override
    protected Class<OrderViewModel> getClassVM() {
        return OrderViewModel.class;
    }

    @Override
    protected void initViews() {

        Order order = (Order) mData;

        viewModel.getShipmentById(order.getShipmentId());

        for (Product product : productList) {
            if (product.getId() == order.getProductId()) {
                binding.tvProductName.setText(product.getName());
                Glide.with(context).load(product.getImage1()).into(binding.ivProduct);
                binding.tvPrice.setText(convertPrice(order.getTotalPrice()));
                binding.tvCount.setText(order.getQuantity() + " sản phẩm");
                binding.tvProductCost.setText(convertPrice(order.getTotalPrice()));
                binding.tvTotalCost.setText(convertPrice(order.getTotalPrice()));
                break;
            }
        }

        if (order.getStatus() == 0) {
            initDeliveringView(order);
        } else if (order.getStatus() == 1) {
            initDeliveredView(order);
        } else if (order.getStatus() == 2) {
            initCompleteView(order);
        } else if (order.getStatus() == 3) {
            initCancelView(order);
        }

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });
        binding.btDoAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (order.getStatus() == 0) {
                    cancelOrderDialog(order.getId(), CANCEL_BILL);
                } else if (order.getStatus() == 1) {
                    callBack.showFragment(EvaluateFragment.TAG, null, true);
                } else if (order.getStatus() == 2) {
                    ShoppingCart cart = new ShoppingCart(user.getId(), order.getProductId(), order.getTotalPrice() / order.getQuantity(), order.getType());
                    viewModel.addShoppingCart(cart);
                } else if (order.getStatus() == 3) {
                    ShoppingCart cart = new ShoppingCart(user.getId(), order.getProductId(), order.getTotalPrice() / order.getQuantity(), order.getType());
                    viewModel.addShoppingCart(cart);
                }

            }
        });
    }

    private void initDeliveringView(Order order) {

        //Status code 0
        binding.tvStatus.setText("Đơn hàng đang giao");
        binding.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.orange_500));
        binding.tvShipName.setText("Đang vận chuyển");
        binding.tvShipInfo.setText("Dự kiến: " + order.getCreateAt());
        binding.tvShipName.setTextColor(ContextCompat.getColor(context, R.color.orange_500));
        binding.btDoAction.setText("Huỷ đơn");
    }

    private void initDeliveredView(Order order) {

        //Status code 1
        binding.tvStatus.setText("Đơn hàng đã hoàn thành");
        binding.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.blue_500));
        binding.tvShipName.setText("Giao hàng thành công");
        binding.tvShipInfo.setText("Thời gian: " + order.getCreateAt());
        binding.tvShipName.setTextColor(ContextCompat.getColor(context, R.color.blue_500));
        binding.btDoAction.setText("Đánh giá");
    }

    private void initCompleteView(Order order) {

        //Status code 2
        binding.tvStatus.setText("Đơn hàng đã hoàn thành");
        binding.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.blue_500));
        binding.tvShipName.setText("Giao hàng thành công");
        binding.tvShipInfo.setText("Thời gian: " + order.getCreateAt());
        binding.tvShipName.setTextColor(ContextCompat.getColor(context, R.color.blue_500));
        binding.btDoAction.setText("Mua lại");
    }

    private void initCancelView(Order order) {

        //Status code 3
        binding.tvStatus.setText("Đơn hàng đã bị huỷ");
        binding.tvStatus.setTextColor(ContextCompat.getColor(context, R.color.error));
        binding.tvShipName.setText("Đơn hàng đã bị huỷ");
        binding.tvShipInfo.setText("Lý do: " + order.getReason());
        binding.tvShipName.setTextColor(ContextCompat.getColor(context, R.color.error));
        binding.btDoAction.setText("Mua lại");
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
    protected FragmentOrderDetailBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentOrderDetailBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_ADD_SHOPPING_CART)) {
            int response = (int) data;
            if (response == -1) {
                Toast.makeText(context, "Thêm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                viewModel.getShoppingCartByAccountId(user.getId());
            }
        } else if (key.equals(Constants.KEY_GET_SHOPPING_CART_BY_ACCOUNT)) {
            MyApplication.getInstance().getStorage().listCart = (List<ShoppingCart>) data;
        } else if (key.equals(Constants.KEY_UPDATE_BILL)) {
            boolean response = (boolean) data;
            if (response) {
                Log.d(TAG, "apiSuccess: thành công");
                viewModel.getBillAccountId(user.getId());
            } else {
                Log.d(TAG, "apiSuccess: thất bại");
            }
        } else if (key.equals(Constants.KEY_GET_BILL)) {
            List<Order> orderList = (List<Order>) data;
            MyApplication.getInstance().getStorage().listOrder = orderList;
        } else if(key.equals(Constants.KEY_GET_SHIPMENT_BY_ID)){
            Shipment shipment = (Shipment) data;
            if (!shipment.isTypeAddress()) {
                binding.tvTypeAddress.setText("Nhà riêng");
            } else {
                binding.tvTypeAddress.setText("Văn phòng");
            }
            binding.tvFullName.setText(shipment.getFullName());
            binding.tvPhone.setText(shipment.getPhoneNumber());
            binding.tvStreet.setText(String.format(shipment.getStreet() + ", " + shipment.getAddress()));
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
    public void setData(Object data) {
        this.mData = data;
    }
}

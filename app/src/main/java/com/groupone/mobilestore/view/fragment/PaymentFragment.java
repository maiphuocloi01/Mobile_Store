package com.groupone.mobilestore.view.fragment;

import static com.groupone.mobilestore.util.NumberUtils.convertPrice;
import static com.groupone.mobilestore.util.NumberUtils.hideCardNumber;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentPaymentBinding;
import com.groupone.mobilestore.model.Bank;
import com.groupone.mobilestore.model.Order;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.util.ViewUtils;
import com.groupone.mobilestore.view.adapter.PaymentAdapter;
import com.groupone.mobilestore.viewmodel.PaymentViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PaymentFragment extends BaseFragment<FragmentPaymentBinding, PaymentViewModel> implements PaymentAdapter.PaymentCallBack {

    public static final String TAG = PaymentFragment.class.getName();
    private final User user = MyApplication.getInstance().getStorage().user;
    private Object mData;
    private List<Integer> listCartIdDelete = new ArrayList<>();
    private int countCart = 0;
    private int temp = 1;
    private List<Order> orderList = new ArrayList<>();

    @Override
    protected Class<PaymentViewModel> getClassVM() {
        return PaymentViewModel.class;
    }

    @Override
    protected void initViews() {

        if (MyApplication.getInstance().getStorage().shipment != null) {
            Shipment shipment = MyApplication.getInstance().getStorage().shipment;
            Log.d(TAG, "viewModel.shipment: ");
            ViewUtils.show(binding.tvFullName);
            ViewUtils.show(binding.tvPhone);
            if (!shipment.isTypeAddress()) {
                binding.tvTypeAddress.setText("Nhà riêng");
            } else {
                binding.tvTypeAddress.setText("Văn phòng");
            }
            binding.tvFullName.setText(shipment.getFullName());
            binding.tvPhone.setText(shipment.getPhoneNumber());
            binding.tvStreet.setText(String.format(shipment.getStreet() + ", " + shipment.getAddress()));
        } else {
            viewModel.getShipmentByAccountId(user.getId());
        }

        Bundle newData = (Bundle) mData;
        if (newData != null) {
            if (newData.getSerializable("carts") != null) {
                Log.d(TAG, "getSerializable");
                CartFragment.ListCart carts = (CartFragment.ListCart) newData.getSerializable("carts");
                viewModel.cartList = carts.cartList;
            }
        }


        if (MyApplication.getInstance().getStorage().bank != null) {
            Bank bank = MyApplication.getInstance().getStorage().bank;
            initChoosePayment(bank);
        } else {
            Bank bank = new Bank(0, "", "Chọn phương thức thanh toán", "", 0, "Thanh toán khi nhận hàng");
            MyApplication.getInstance().getStorage().bank = bank;
            initChoosePayment(bank);
        }

        if (viewModel.cartList.size() > 0) {
            Log.d(TAG, "carts: " + viewModel.cartList.size());
            long productCost = 0L;
            long shipCost = 0L;
            long totalCost = 0L;
            for (ShoppingCart item : viewModel.cartList) {
                productCost += item.getQuantity() * item.getPrice();
            }
            totalCost = shipCost + productCost;
            binding.tvProductCost.setText(convertPrice(productCost));
            binding.tvTotalCost.setText(convertPrice(totalCost));
            PaymentAdapter paymentAdapter = new PaymentAdapter(context, viewModel.cartList, this);
            binding.rvProduct.setAdapter(paymentAdapter);
        }

        binding.layoutAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(ChooseAddressFragment.TAG, null, true);
            }
        });

        binding.layoutShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(ChooseShipmentFragment.TAG, null, true);
            }
        });

        binding.layoutPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(ChoosePaymentFragment.TAG, null, true);
            }
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        binding.btPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.getInstance().getStorage().shipment == null) {
                    Toast.makeText(context, "Bạn chưa có địa chỉ giao hàng", Toast.LENGTH_SHORT).show();
                } else {
                    showAlertDialog();
                }
            }
        });

    }

    private void initChoosePayment(Bank bank) {
        binding.tvPayName.setText(bank.getBrand());
        if (bank.getId() == 0) {
            binding.tvPayInfo.setText(bank.getCardNumber());
        } else {
            binding.tvPayInfo.setText(hideCardNumber(bank.getCardNumber()));
        }
    }

    private void showAlertDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_alert_dialog);
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

        TextView tvTitle = dialog.findViewById(R.id.tv_title);
        TextView tvDescription = dialog.findViewById(R.id.tv_description);
        Button btnCancel = dialog.findViewById(R.id.bt_cancel3);
        Button btnConfirm = dialog.findViewById(R.id.bt_confirm3);

        tvTitle.setText("Thông báo");
        tvDescription.setText("Thông báo xác nhận thanh toán");

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnConfirm.setOnClickListener(view -> {
            Shipment shipment = MyApplication.getInstance().getStorage().shipment;

            Bank bank = MyApplication.getInstance().getStorage().bank;

            String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
            int bankId = 0;
            if (bank.getId() != 0) {
                bankId = bank.getId();
            }

            for (ShoppingCart item : viewModel.cartList) {
                Order order = new Order(
                        item.getProductId(),
                        shipment.getId(),
                        user.getId(),
                        timeStamp,
                        item.getQuantity(),
                        0,
                        item.getQuantity() * item.getPrice(),
                        bankId,
                        "",
                        item.getTypeProduct()
                );
                orderList.add(order);
                listCartIdDelete.add(item.getId());
                //viewModel.deleteItemCartById(item.getId());
            }
            countCart = orderList.size();
            viewModel.addBill(orderList.get(0));
            dialog.dismiss();
            DialogUtils.showLoadingDialog(context);
        });

        dialog.show();
    }

    @Override
    protected FragmentPaymentBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentPaymentBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_GET_SHIPMENT_BY_ACCOUNT)) {
            List<Shipment> listAddress = (List<Shipment>) data;
            Log.d(TAG, "apiSuccess: " + listAddress.size());
            if (listAddress.size() > 0) {
                ViewUtils.show(binding.tvFullName);
                ViewUtils.show(binding.tvPhone);
                Shipment shipment = listAddress.get(0);
                MyApplication.getInstance().getStorage().shipment = shipment;
                if (!shipment.isTypeAddress()) {
                    binding.tvTypeAddress.setText("Nhà riêng");
                } else {
                    binding.tvTypeAddress.setText("Văn phòng");
                }
                binding.tvFullName.setText(shipment.getFullName());
                binding.tvPhone.setText(shipment.getPhoneNumber());
                binding.tvStreet.setText(String.format(shipment.getStreet() + ", " + shipment.getAddress()));
            }
        } else if (key.equals(Constants.KEY_ADD_BILL)) {
            Log.d(TAG, "apiSuccess: KEY_ADD_BILL");
            Log.d(TAG, "countCart: " + countCart);
            Log.d(TAG, "temp: " + temp);
            if(countCart > 1 && temp < countCart){
                Log.d(TAG, "addBill+: ");
                viewModel.addBill(orderList.get(temp));
                temp++;
            } else if (temp == countCart){
                DialogUtils.hideLoadingDialog();
                callBack.showFragment(CompletedFragment.TAG, listCartIdDelete, false);
            }
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {
        DialogUtils.hideLoadingDialog();
    }

    @Override
    public void setData(Object data) {
        this.mData = data;
    }

    @Override
    public void gotoProductDetail(int productId) {

    }


}

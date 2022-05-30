package com.groupone.mobilestore.view.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.R;
import com.groupone.mobilestore.databinding.FragmentBankBinding;
import com.groupone.mobilestore.model.Bank;
import com.groupone.mobilestore.model.Favorite;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.view.adapter.AddressAdapter;
import com.groupone.mobilestore.view.adapter.BankAdapter;
import com.groupone.mobilestore.viewmodel.BankViewModel;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class BankFragment extends BaseFragment<FragmentBankBinding, BankViewModel> implements BankAdapter.BankCallBack {

    public static final String TAG = BankFragment.class.getName();
    private List<Bank> listBank = new ArrayList<>();
    private User user = MyApplication.getInstance().getStorage().user;

    @Override
    protected Class<BankViewModel> getClassVM() {
        return BankViewModel.class;
    }

    @Override
    protected void initViews() {

        viewModel.getAllBank(user.getId());

        //listBank.add(new Bank(1, "Mai Phước Lợi", "1234567890121234", "10/23", 123, "Thẻ VISA"));
        //listBank.add(new Bank(2, "Mai Phước Lợi", "1234567890121234", "", 0, "Vietcombank"));



        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });
        binding.frameAddBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(AddBankFragment.TAG, null, true);
            }
        });

        binding.frameAddVisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(AddVisaFragment.TAG, null, true);
            }
        });

    }

    @Override
    protected FragmentBankBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentBankBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_GET_BANK)) {
            List<Bank> banks = (List<Bank>) data;
            listBank = banks;
            //MyApplication.getInstance().getStorage().listBank = banks;
            binding.rvBank.setLayoutManager(new LinearLayoutManager(context));
            BankAdapter bankAdapter = new BankAdapter(context, listBank, this);
            binding.rvBank.setAdapter(bankAdapter);
        } else if(key.equals(Constants.KEY_DELETE_BANK)){
            boolean response = (boolean) data;
            if(!response){
                Log.d(TAG, "apiSuccess: thất bại");
            } else {
                Log.d(TAG, "apiSuccess: thành công");
                viewModel.getAllBank(user.getId());
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
    public void deleteBank(int id) {
        showAlertDialog(id);
    }

    private void showAlertDialog(int id) {
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
        tvDescription.setText("Bạn có muốn xoá số tài khoản này không?");

        btnCancel.setOnClickListener(view -> dialog.dismiss());

        btnConfirm.setOnClickListener(view -> {
            viewModel.deleteBank(id);
            dialog.dismiss();
        });

        dialog.show();
    }
}

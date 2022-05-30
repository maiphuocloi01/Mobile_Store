package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentChoosePaymentBinding;
import com.groupone.mobilestore.model.Bank;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.view.adapter.BankAdapter;
import com.groupone.mobilestore.view.adapter.ChoosePayAdapter;
import com.groupone.mobilestore.viewmodel.BankViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChoosePaymentFragment extends BaseFragment<FragmentChoosePaymentBinding, BankViewModel> implements ChoosePayAdapter.ChoosePayCallBack {

    public static final String TAG = ChoosePaymentFragment.class.getName();
    private List<Bank> listBank = new ArrayList<>();
    private final User user = MyApplication.getInstance().getStorage().user;


    @Override
    protected Class<BankViewModel> getClassVM() {
        return BankViewModel.class;
    }

    @Override
    protected void initViews() {

        viewModel.getAllBank(user.getId());

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        binding.layoutPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bank bank = new Bank(0, "", "Chọn phương thức thanh toán", "", 0, "Thanh toán khi nhận hàng");
                MyApplication.getInstance().getStorage().bank = bank;
                callBack.backToPrev();
            }
        });

        binding.frameAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.showFragment(BankFragment.TAG, null, true);
            }
        });

    }

    @Override
    protected FragmentChoosePaymentBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentChoosePaymentBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if (key.equals(Constants.KEY_GET_BANK)) {
            List<Bank> banks = (List<Bank>) data;
            listBank = banks;
            //MyApplication.getInstance().getStorage().listBank = banks;
            binding.rvBank.setLayoutManager(new LinearLayoutManager(context));
            ChoosePayAdapter bankAdapter = new ChoosePayAdapter(context, listBank, this);
            binding.rvBank.setAdapter(bankAdapter);
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {

    }

    @Override
    public void choosePayment(Bank bank) {
        MyApplication.getInstance().getStorage().bank = bank;
        callBack.backToPrev();
    }
}

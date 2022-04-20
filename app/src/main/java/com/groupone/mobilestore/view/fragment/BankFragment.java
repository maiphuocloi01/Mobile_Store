package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.databinding.FragmentBankBinding;
import com.groupone.mobilestore.model.Bank;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.view.adapter.AddressAdapter;
import com.groupone.mobilestore.view.adapter.BankAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class BankFragment extends BaseFragment<FragmentBankBinding, CommonViewModel> {

    public static final String TAG = BankFragment.class.getName();

    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

        List<Bank> listBank = new ArrayList<>();

        listBank.add(new Bank(1, "Mai Phước Lợi", "1234567890121234", "10/23", 123, "Thẻ VISA"));
        listBank.add(new Bank(2, "Mai Phước Lợi", "1234567890121234", "", 0, "Vietcombank"));

        binding.rvBank.setLayoutManager(new LinearLayoutManager(context));
        BankAdapter bankAdapter = new BankAdapter(context, listBank);
        binding.rvBank.setAdapter(bankAdapter);

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

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}

package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentBankBinding;
import com.groupone.mobilestore.model.Bank;
import com.groupone.mobilestore.model.Favorite;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.view.adapter.AddressAdapter;
import com.groupone.mobilestore.view.adapter.BankAdapter;
import com.groupone.mobilestore.viewmodel.BankViewModel;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class BankFragment extends BaseFragment<FragmentBankBinding, BankViewModel> {

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
            BankAdapter bankAdapter = new BankAdapter(context, listBank);
            binding.rvBank.setAdapter(bankAdapter);
        }
    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}

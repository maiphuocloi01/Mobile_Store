package com.groupone.mobilestore.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.databinding.FragmentAddBankBinding;
import com.groupone.mobilestore.model.Ward;
import com.groupone.mobilestore.view.adapter.BankBrandAdapter;
import com.groupone.mobilestore.view.adapter.WardAdapter;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddBankFragment extends BaseFragment<FragmentAddBankBinding, CommonViewModel>{

    public static final String TAG = AddBankFragment.class.getName();
    private List<String> listBank = new ArrayList<>();
    @Override
    protected Class<CommonViewModel> getClassVM() {
        return CommonViewModel.class;
    }

    @Override
    protected void initViews() {

        listBank.add("Vietcombank");
        listBank.add("BIDV");
        listBank.add("Techcombank");
        listBank.add("Vietinbank");

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        binding.etBankType.setCursorVisible(false);
        binding.etBankType.setShowSoftInputOnFocus(false);
        binding.etBankType.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    if(listBank != null) {
                        showBankPickerDialog(listBank);
                    }
                }
            }
        });
        binding.etBankType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listBank != null) {
                    showBankPickerDialog(listBank);
                }
            }
        });
    }

    @Override
    protected FragmentAddBankBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentAddBankBinding.inflate(inflater, container, false);
    }

    private void showBankPickerDialog(List<String> listBank) {
        BankBottomSheetFragment bankBottomSheetFragment = new BankBottomSheetFragment(listBank, new BankBrandAdapter.BankCallback() {
            @Override
            public void chooseBank(int position, String name) {
                binding.etBankType.setText(name);
            }
        });
        bankBottomSheetFragment.show(getParentFragmentManager(), bankBottomSheetFragment.getTag());
    }

    @Override
    public void apiSuccess(String key, Object data) {

    }

    @Override
    public void apiError(String key, int code, Object data) {

    }
}

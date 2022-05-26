package com.groupone.mobilestore.view.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentAddBankBinding;
import com.groupone.mobilestore.model.Bank;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.model.Ward;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.view.adapter.BankBrandAdapter;
import com.groupone.mobilestore.view.adapter.WardAdapter;
import com.groupone.mobilestore.viewmodel.BankViewModel;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddBankFragment extends BaseFragment<FragmentAddBankBinding, BankViewModel>{

    public static final String TAG = AddBankFragment.class.getName();
    private List<String> listBank = new ArrayList<>();
    private User user = MyApplication.getInstance().getStorage().user;
    @Override
    protected Class<BankViewModel> getClassVM() {
        return BankViewModel.class;
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

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.etName.getText())) {
                    binding.etName.setError("Không được bỏ trống");

                } else if (TextUtils.isEmpty(binding.etBankType.getText())) {
                    binding.etBankType.setError("Không được bỏ trống");

                } else if (TextUtils.isEmpty(binding.etCardNumber.getText())) {
                    binding.etCardNumber.setError("Không được bỏ trống");

                } else {

                    String name = binding.etName.getText().toString().trim();
                    String bankType = binding.etBankType.getText().toString().trim();
                    String cardNumber = binding.etCardNumber.getText().toString().trim();
                    Bank bank = new Bank(name,
                            cardNumber,
                            "",
                            0,
                            bankType,
                            user.getId()
                    );
                    viewModel.addBank(bank);
                    DialogUtils.showLoadingDialog(context);
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
        if(key.equals(Constants.KEY_ADD_BANK)){
            int response = (int) data;
            DialogUtils.hideLoadingDialog();
            if(response == -1){
                Toast.makeText(context, "Thêm ngân hàng thất bại", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Thêm ngân hàng thành công", Toast.LENGTH_SHORT).show();
                callBack.backToPrev();
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
}

package com.groupone.mobilestore.view.fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.groupone.mobilestore.MyApplication;
import com.groupone.mobilestore.databinding.FragmentAddVisaBinding;
import com.groupone.mobilestore.model.Bank;
import com.groupone.mobilestore.model.User;
import com.groupone.mobilestore.util.Constants;
import com.groupone.mobilestore.util.DialogUtils;
import com.groupone.mobilestore.viewmodel.BankViewModel;
import com.groupone.mobilestore.viewmodel.CommonViewModel;

public class AddVisaFragment extends BaseFragment<FragmentAddVisaBinding, BankViewModel>{

    public static final String TAG = AddVisaFragment.class.getName();

    private User user = MyApplication.getInstance().getStorage().user;

    @Override
    protected Class<BankViewModel> getClassVM() {
        return BankViewModel.class;
    }

    @Override
    protected void initViews() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.backToPrev();
            }
        });

        binding.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s=editable.toString();
                if(!s.equals(s.toUpperCase()))
                {
                    s=s.toUpperCase();
                    binding.etName.setText(s);
                    binding.etName.setSelection(binding.etName.length()); //fix reverse texting
                }
            }
        });

        binding.etDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s=editable.toString();
                if(s.length() == 2)
                {
                    s = s + "/";
                    binding.etDate.setText(s);
                    binding.etDate.setSelection(binding.etDate.length()); //fix reverse texting
                }
            }
        });

        binding.etCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s=editable.toString();
                if(s.length() == 4)
                {
                    s = s + " ";
                    binding.etCardNumber.setText(s);
                    binding.etCardNumber.setSelection(binding.etCardNumber.length()); //fix reverse texting
                } else if(s.length() == 9)
                {
                    s = s + " ";
                    binding.etCardNumber.setText(s);
                    binding.etCardNumber.setSelection(binding.etCardNumber.length()); //fix reverse texting
                } else if(s.length() == 14)
                {
                    s = s + " ";
                    binding.etCardNumber.setText(s);
                    binding.etCardNumber.setSelection(binding.etCardNumber.length()); //fix reverse texting
                }
            }
        });

        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.etName.getText())) {
                    binding.etName.setError("Không được bỏ trống");

                } else if (TextUtils.isEmpty(binding.etDate.getText())) {
                    binding.etDate.setError("Không được bỏ trống");

                } else if (TextUtils.isEmpty(binding.etCardNumber.getText())) {
                    binding.etCardNumber.setError("Không được bỏ trống");

                } else if (TextUtils.isEmpty(binding.etCvv.getText())) {
                    binding.etCvv.setError("Không được bỏ trống");

                } else {

                    String name = binding.etName.getText().toString().trim().replaceAll("\\s", "");;
                    String date = binding.etDate.getText().toString().trim();
                    int cvv = Integer.parseInt(binding.etCvv.getText().toString().trim());
                    String cardNumber = binding.etCardNumber.getText().toString().trim();
                    Bank bank = new Bank(name,
                            cardNumber,
                            date,
                            cvv,
                            "Thẻ VISA",
                            user.getId()
                    );
                    viewModel.addBank(bank);
                    DialogUtils.showLoadingDialog(context);
                }
            }
        });
    }

    @Override
    protected FragmentAddVisaBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentAddVisaBinding.inflate(inflater, container, false);
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if(key.equals(Constants.KEY_ADD_BANK)){
            int response = (int) data;
            DialogUtils.hideLoadingDialog();
            if(response == -1){
                Log.d(TAG, "apiSuccess: thất bại" + response);
            } else {
                Log.d(TAG, "apiSuccess: thành công" + response);
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

package com.groupone.mobilestore.viewmodel;

import com.groupone.mobilestore.model.Bank;
import com.groupone.mobilestore.util.Constants;

public class BankViewModel extends BaseViewModel{

    private static final String TAG = BankViewModel.class.getName();

    public void getAllBank(int id){
        getApi().getBankByAccountId(id).enqueue(initHandleResponse(Constants.KEY_GET_BANK));
    }

    public void deleteBank(int id){
        getApi().deleteBankById(id).enqueue(initHandleResponse(Constants.KEY_DELETE_BANK));
    }

    public void addBank(Bank bank){
        getApi().addBank(bank).enqueue(initHandleResponse(Constants.KEY_ADD_BANK));
    }
}

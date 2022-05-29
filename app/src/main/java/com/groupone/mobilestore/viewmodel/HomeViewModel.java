package com.groupone.mobilestore.viewmodel;

import com.groupone.mobilestore.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel{

    public List<String> listFilterBrand = new ArrayList<>();
    public List<String> listFilterPrice = new ArrayList<>();
    public List<String> listFilterCategory = new ArrayList<>();
    public List<String> listFilterRam = new ArrayList<>();
    public List<String> listFilterRom = new ArrayList<>();
    public List<String> listFilterScreen = new ArrayList<>();

    private static final String TAG = HomeViewModel.class.getName();

    public void getTopSaleProduct(){
        getApi().getTopSaleProduct().enqueue(initHandleResponse(Constants.KEY_GET_TOP_SALE_PRODUCT));
    }


}

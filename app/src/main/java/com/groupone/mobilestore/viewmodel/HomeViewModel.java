package com.groupone.mobilestore.viewmodel;

import com.groupone.mobilestore.util.Constants;

public class HomeViewModel extends BaseViewModel{

    private static final String TAG = HomeViewModel.class.getName();

    public void getTopSaleProduct(){
        getApi().getTopSaleProduct().enqueue(initHandleResponse(Constants.KEY_GET_TOP_SALE_PRODUCT));
    }


}

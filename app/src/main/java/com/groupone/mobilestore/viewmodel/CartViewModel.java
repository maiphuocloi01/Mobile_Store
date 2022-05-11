package com.groupone.mobilestore.viewmodel;

import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.util.Constants;

public class CartViewModel extends BaseViewModel{

    private static final String TAG = CartViewModel.class.getName();

    public void getShoppingCartByAccountId(int id){
        getApi().getShoppingCartByAccountId(id).enqueue(initHandleResponse(Constants.KEY_GET_SHOPPING_CART_BY_ACCOUNT));
    }

    public void deleteItemCartById(int id){
        getApi().deleteItemCartById(id).enqueue(initHandleResponse(Constants.KEY_DELETE_CART));
    }
}

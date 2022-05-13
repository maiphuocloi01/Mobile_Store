package com.groupone.mobilestore.viewmodel;

import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.util.Constants;

public class ProductViewModel extends BaseViewModel{

    private static final String TAG = ProductViewModel.class.getName();


    public void addShoppingCart(ShoppingCart cart){
        getApi().addShoppingCart(cart).enqueue(initHandleResponse(Constants.KEY_ADD_SHOPPING_CART));
    }

    public void getShoppingCartByAccountId(int id){
        getApi().getShoppingCartByAccountId(id).enqueue(initHandleResponse(Constants.KEY_GET_SHOPPING_CART_BY_ACCOUNT));
    }
}
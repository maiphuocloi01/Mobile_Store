package com.groupone.mobilestore.viewmodel;

import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.util.Constants;

public class ProductViewModel extends BaseViewModel{

    private static final String TAG = AccountViewModel.class.getName();

    public void addShoppingCart(ShoppingCart cart){
        getApi().addShoppingCart(cart).enqueue(initHandleResponse(Constants.KEY_ADD_SHOPPING_CART));
    }
}

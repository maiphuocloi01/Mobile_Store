package com.groupone.mobilestore.viewmodel;

import com.groupone.mobilestore.model.Order;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.util.Constants;

public class OrderViewModel extends BaseViewModel{

    private static final String TAG = OrderViewModel.class.getName();

    public void getBillAccountId(int id){
        getApi().getBillAccountId(id).enqueue(initHandleResponse(Constants.KEY_GET_BILL));
    }

    public void changeBillStatus(int id, int status){
        getApi().changeBillStatus(id, status).enqueue(initHandleResponse(Constants.KEY_CHANGE_STATUS_BILL));
    }

    public void updateBill(Order order){
        getApi().updateOrder(order).enqueue(initHandleResponse(Constants.KEY_UPDATE_BILL));
    }

    public void addShoppingCart(ShoppingCart cart){
        getApi().addShoppingCart(cart).enqueue(initHandleResponse(Constants.KEY_ADD_SHOPPING_CART));
    }

    public void getShoppingCartByAccountId(int id){
        getApi().getShoppingCartByAccountId(id).enqueue(initHandleResponse(Constants.KEY_GET_SHOPPING_CART_BY_ACCOUNT));
    }
}

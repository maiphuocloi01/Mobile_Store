package com.groupone.mobilestore.viewmodel;

import com.groupone.mobilestore.model.Order;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class PaymentViewModel extends BaseViewModel{

    private static final String TAG = PaymentViewModel.class.getName();

    public List<ShoppingCart> cartList = new ArrayList<>();

    public void getShipmentByAccountId(int id) {
        getApi().getShipmentByAccountId(id).enqueue(initHandleResponse(Constants.KEY_GET_SHIPMENT_BY_ACCOUNT));
    }

    public void getAllBank(int id){
        getApi().getBankByAccountId(id).enqueue(initHandleResponse(Constants.KEY_GET_BANK));
    }

    public void addBill(Order order){
        getApi().addOrder(order).enqueue(initHandleResponse(Constants.KEY_ADD_BILL));
    }

    public void deleteItemCartById(int id){
        getApi().deleteItemCartById(id).enqueue(initHandleResponse(Constants.KEY_DELETE_CART));
    }

}

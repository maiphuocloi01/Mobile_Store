package com.groupone.mobilestore.viewmodel;

import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ShipmentViewModel extends BaseViewModel {

    private static final String TAG = ShipmentViewModel.class.getName();



    public void addShipment(Shipment shipment) {
        getApi().addShipment(shipment).enqueue(initHandleResponse(Constants.KEY_ADD_SHIPMENT));
    }

    public void getShipmentByAccountId(int id) {
        getApi().getShipmentByAccountId(id).enqueue(initHandleResponse(Constants.KEY_GET_SHIPMENT_BY_ACCOUNT));
    }

    public void getShipmentById(int id) {
        getApi().getShipmentById(id).enqueue(initHandleResponse(Constants.KEY_GET_SHIPMENT_BY_ID));
    }

    public void updateShipment(Shipment shipment) {
        getApi().updateShipment(shipment).enqueue(initHandleResponse(Constants.KEY_UPDATE_SHIPMENT));
    }

    public void deleteShipmentById(int id) {
        getApi().deleteShipmentById(id).enqueue(initHandleResponse(Constants.KEY_DELETE_SHIPMENT));
    }
}

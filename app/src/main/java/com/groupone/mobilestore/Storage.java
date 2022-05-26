package com.groupone.mobilestore;

import com.groupone.mobilestore.model.Bank;
import com.groupone.mobilestore.model.Favorite;
import com.groupone.mobilestore.model.Product;
import com.groupone.mobilestore.model.Shipment;
import com.groupone.mobilestore.model.ShoppingCart;
import com.groupone.mobilestore.model.User;

import java.util.List;

public class Storage {
    public List<Product> listProduct;
    public List<ShoppingCart> listCart;
    public List<Shipment> listShipment;
    public User user;
    public List<Favorite> listFavorite;
    //public List<Bank> listBank;
}

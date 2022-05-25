package com.groupone.mobilestore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Favorite implements Serializable {

    @SerializedName("Id")
    private int id;
    @SerializedName("ProductId")
    private int productId;
    @SerializedName("AccountId")
    private int accountId;

    public Favorite(int id, int productId, int accountId) {
        this.id = id;
        this.productId = productId;
        this.accountId = accountId;
    }

    public Favorite(int productId, int accountId) {
        this.productId = productId;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}

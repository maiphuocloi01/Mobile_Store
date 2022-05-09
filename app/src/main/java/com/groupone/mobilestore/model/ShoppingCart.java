package com.groupone.mobilestore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShoppingCart implements Serializable {

    @SerializedName("Id")
    private int id;
    @SerializedName("AccountId")
    private int accountId;
    @SerializedName("ProductId")
    private int productId;
    @SerializedName("Quantity")
    private int quantity;
    @SerializedName("Price")
    private long price;
    @SerializedName("TypeProduct")
    private String typeProduct;
    private boolean isSelected = false;
    private String image;

    public ShoppingCart(int accountId, int productId, long price, String typeProduct) {
        this.accountId = accountId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.typeProduct = typeProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

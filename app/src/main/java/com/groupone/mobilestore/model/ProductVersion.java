package com.groupone.mobilestore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductVersion implements Serializable {

    @SerializedName("Id")
    private int id;

    @SerializedName("ProductId")
    private int productId;

    @SerializedName("VersionName")
    private String versionName;

    @SerializedName("Color")
    private String color;

    @SerializedName("Price")
    private long price;

    public ProductVersion(int id, int productId, String versionName, String color, long price) {
        this.id = id;
        this.productId = productId;
        this.versionName = versionName;
        this.color = color;
        this.price = price;
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

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}

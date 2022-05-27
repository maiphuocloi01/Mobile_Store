package com.groupone.mobilestore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable {

    @SerializedName("Id")
    private int id;
    @SerializedName("ProductId")
    private int productId;
    @SerializedName("ShipmentId")
    private int shipmentId;
    @SerializedName("AccountId")
    private int accountId;
    private String productName;
    private int image;
    @SerializedName("CreateAt")
    private String createAt;
    @SerializedName("Quantity")
    private int quantity;
    @SerializedName("Status")
    private int status; // = 0 đang giao, = 1 đã giao (chưa đánh giá), = 2 đã đánh giá (mua lại), = 3 đã huỷ (do người dùng huỷ)
    @SerializedName("TotalPrice")
    private int totalPrice;
    @SerializedName("ShipCost")
    private int shipCost;
    @SerializedName("Reason")
    private String reason;
    @SerializedName("TypeProduct")
    private String type; //Phân loại sản phẩm VD: 128GB, Xám
    private Shipment shipment;

    public Order(int id, String productName, int image, String createAt, int quantity, int status, int totalPrice, int shipCost, String reason, String type, Shipment shipment) {
        this.id = id;
        this.productName = productName;
        this.image = image;
        this.createAt = createAt;
        this.quantity = quantity;
        this.status = status;
        this.totalPrice = totalPrice;
        this.shipCost = shipCost;
        this.reason = reason;
        this.type = type;
        this.shipment = shipment;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getShipCost() {
        return shipCost;
    }

    public void setShipCost(int shipCost) {
        this.shipCost = shipCost;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}

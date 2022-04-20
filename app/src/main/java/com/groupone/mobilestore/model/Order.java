package com.groupone.mobilestore.model;

public class Order {

    private int id;
    private String productName;
    private int image;
    private String createAt;
    private int quantity;
    private int status; // = 0 đang giao, = 1 đã giao (chưa đánh giá), = 2 đã đánh giá (mua lại), = 3 đã huỷ (do người dùng huỷ)
    private int totalPrice;
    private int shipCost;
    private String reason;
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

package com.groupone.mobilestore.model;

public class Bank {

    private int id;
    private String name;
    private String cardNumber;
    private String expiredDate;
    private int CVV;
    private String brand;

    public Bank(int id, String name, String cardNumber, String expiredDate, int CVV, String brand) {
        this.id = id;
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiredDate = expiredDate;
        this.CVV = CVV;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

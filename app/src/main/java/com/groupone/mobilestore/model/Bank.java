package com.groupone.mobilestore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bank implements Serializable {

    @SerializedName("Id")
    private int id;
    @SerializedName("AccountId")
    private int accountId;
    @SerializedName("FullName")
    private String name;
    @SerializedName("CardNumber")
    private String cardNumber;
    @SerializedName("ExpiredDate")
    private String expiredDate;
    @SerializedName("CVV")
    private int CVV;
    @SerializedName("Brand")
    private String brand;

    public Bank(int id, String name, String cardNumber, String expiredDate, int CVV, String brand) {
        this.id = id;
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiredDate = expiredDate;
        this.CVV = CVV;
        this.brand = brand;
    }

//    public Bank(int accountId, String name, String cardNumber, String brand) {
//        this.accountId = accountId;
//        this.name = name;
//        this.cardNumber = cardNumber;
//        this.brand = brand;
//    }

    public Bank(String name, String cardNumber, String expiredDate, int CVV, String brand, int accountId) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiredDate = expiredDate;
        this.CVV = CVV;
        this.brand = brand;
        this.accountId = accountId;
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

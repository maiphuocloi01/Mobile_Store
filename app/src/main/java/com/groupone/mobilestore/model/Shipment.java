package com.groupone.mobilestore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Shipment implements Serializable {

    @SerializedName("Id")
    private int id;
    @SerializedName("AccountId")
    private int accountId;
    @SerializedName("FullName")
    private String fullName;
    @SerializedName("PhoneNumber")
    private String phoneNumber;
    @SerializedName("Address")
    private String address;
    @SerializedName("Street")
    private String street;
    @SerializedName("TypeAddress")
    private boolean typeAddress;
    @SerializedName("IsDefault")
    private boolean isDefault;

    public Shipment(int id, int accountId, String fullName, String phoneNumber, String address, String street, boolean typeAddress, boolean isDefault) {
        this.id = id;
        this.accountId = accountId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.street = street;
        this.typeAddress = typeAddress;
        this.isDefault = isDefault;
    }

    public Shipment(int accountId, String fullName, String phoneNumber, String address, String street, boolean typeAddress, boolean isDefault) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.street = street;
        this.typeAddress = typeAddress;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isTypeAddress() {
        return typeAddress;
    }

    public void setTypeAddress(boolean typeAddress) {
        this.typeAddress = typeAddress;
    }

    public Shipment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}

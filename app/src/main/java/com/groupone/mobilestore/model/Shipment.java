package com.groupone.mobilestore.model;

public class Shipment {
    private int id;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String street;
    private int typeAddress;
    private boolean isDefault;

    public Shipment(int id, String fullName, String phoneNumber, String address, String street, int typeAddress, boolean isDefault) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.street = street;
        this.typeAddress = typeAddress;
        this.isDefault = isDefault;
    }

    public int getTypeAddress() {
        return typeAddress;
    }

    public void setTypeAddress(int typeAddress) {
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

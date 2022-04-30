package com.groupone.mobilestore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductDetail implements Serializable {

    @SerializedName("Id")
    private int id;

    @SerializedName("ProductId")
    private int productId;

    @SerializedName("Screen")
    private String screen;

    @SerializedName("OS")
    private String os;

    @SerializedName("FrontCamera")
    private String frontCamera;

    @SerializedName("BackCamera")
    private String backCamera;

    @SerializedName("Chip")
    private String chip;

    @SerializedName("RAM")
    private String ram;

    @SerializedName("Memory")
    private String memory;

    @SerializedName("SIM")
    private String sim;

    @SerializedName("Battery")
    private String battery;

    public ProductDetail(int id, int productId, String screen, String os, String frontCamera, String backCamera,
                         String chip, String ram, String memory, String sim, String battery) {
        this.id = id;
        this.productId = productId;
        this.screen = screen;
        this.os = os;
        this.frontCamera = frontCamera;
        this.backCamera = backCamera;
        this.chip = chip;
        this.ram = ram;
        this.memory = memory;
        this.sim = sim;
        this.battery = battery;
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

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
    }

    public String getBackCamera() {
        return backCamera;
    }

    public void setBackCamera(String backCamera) {
        this.backCamera = backCamera;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }
}

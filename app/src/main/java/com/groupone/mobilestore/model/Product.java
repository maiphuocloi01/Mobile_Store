package com.groupone.mobilestore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    @SerializedName("Id")
    private int id;
    @SerializedName("Brand")
    private String brand;
    @SerializedName("Category")
    private boolean category;
    @SerializedName("Image1")
    private String image1;
    @SerializedName("Image2")
    private String image2;
    @SerializedName("Image3")
    private String image3;
    @SerializedName("Image4")
    private String image4;
    @SerializedName("Description")
    private String description;
    @SerializedName("ProductName")
    private String name;

    private String type;
    @SerializedName("Rating")
    private float rate;
    @SerializedName("Price")
    private long price;
    @SerializedName("SellCount")
    private int sellCount;
    private int countReview;
    @SerializedName("Memory")
    private int memory;
    @SerializedName("RAM")
    private int ram;
    @SerializedName("ScreenSize")
    private float screenSize;

    @SerializedName("ProductVersions")
    private List<ProductVersion> productVersions;

    @SerializedName("ProductDetails")
    private List<ProductDetail> productDetails;

    @SerializedName("Comments")
    private List<Comment> comments;

    public Product(int id, String image1, String name, String type, float rate, long price, int countReview) {
        this.id = id;
        this.image1 = image1;
        this.name = name;
        this.type = type;
        this.rate = rate;
        this.price = price;
        this.countReview = countReview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getCountReview() {
        return countReview;
    }

    public void setCountReview(int countReview) {
        this.countReview = countReview;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isCategory() {
        return category;
    }

    public void setCategory(boolean category) {
        this.category = category;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSellCount() {
        return sellCount;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }

    public List<ProductVersion> getProductVersions() {
        return productVersions;
    }

    public void setProductVersions(List<ProductVersion> productVersions) {
        this.productVersions = productVersions;
    }

    public List<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

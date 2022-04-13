package com.groupone.mobilestore.model;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private int image;
    private String name;
    private String type;
    private double rate;
    private long price;
    private int countReview;

    public Product(int id, int image, String name, String type, double rate, long price, int countReview) {
        this.id = id;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
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

}

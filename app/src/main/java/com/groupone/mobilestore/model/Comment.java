package com.groupone.mobilestore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comment implements Serializable {
    @SerializedName("Id")
    private int id;
    @SerializedName("ProductId")
    private int productId;
    @SerializedName("AccountId")
    private int accountId;
    @SerializedName("FullName")
    private String fullName;
    @SerializedName("CreateAt")
    private String createAt;
    @SerializedName("TypeProduct")
    private String type;
    @SerializedName("Content")
    private String content;
    @SerializedName("Rating")
    private int rating;

    public Comment(int id, String fullName, String createAt, String type, String content, int rating) {
        this.id = id;
        this.fullName = fullName;
        this.createAt = createAt;
        this.type = type;
        this.content = content;
        this.rating = rating;
    }

    public Comment(int productId, int accountId, String fullName, String createAt, String type, String content, int rating) {
        this.productId = productId;
        this.accountId = accountId;
        this.fullName = fullName;
        this.createAt = createAt;
        this.type = type;
        this.content = content;
        this.rating = rating;
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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Comment{" +
                ", productId=" + productId +
                ", accountId=" + accountId +
                ", fullName='" + fullName + '\'' +
                ", createAt='" + createAt + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }
}

package com.groupone.mobilestore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Post implements Serializable {

    @SerializedName("Id")
    private int id;
    @SerializedName("Title")
    private String title;
    @SerializedName("Link")
    private String link;
    @SerializedName("Image")
    private String image;

    public Post(int id, String title, String link, String image) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

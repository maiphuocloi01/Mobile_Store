package com.groupone.mobilestore.model;

public class Comment {
    private int id;
    private String fullName;
    private String createAt;
    private String type;
    private String content;
    private int rating;

    public Comment(int id, String fullName, String createAt, String type, String content, int rating) {
        this.id = id;
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
}

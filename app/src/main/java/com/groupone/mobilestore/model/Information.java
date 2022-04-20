package com.groupone.mobilestore.model;

public class Information {
    private int id;
//    private String screen;
//    private String os;
//    private String frontCamera;
//    private String backCamera;
//    private String cpu;
//    private String ram;
//    private String memory;
//    private String sim;
//    private String battery;
    private String title;
    private String description;

    public Information(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

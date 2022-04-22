package com.groupone.mobilestore.model;

import java.util.List;

public class District {
    private String name;
    private int code;
    private List<Ward> wards;

    public District(String name, int code, List<Ward> wards) {
        this.name = name;
        this.code = code;
        this.wards = wards;
    }

    public District() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }
}

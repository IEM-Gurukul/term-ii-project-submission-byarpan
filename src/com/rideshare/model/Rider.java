package com.rideshare.model;

public class Rider extends User {

    private String area;

    public Rider(String userId, String name, String phone, String area) {
        super(userId, name, phone);
        this.area = area;
    }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    @Override
    public String getRole() {
        return "RIDER";
    }

    @Override
    public String toString() {
        return super.toString() + " | Area: " + area;
    }
}
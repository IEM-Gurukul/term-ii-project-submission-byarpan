package com.rideshare.model;

public class Location {

    private String area;
    private String description;

    public Location(String area, String description) {
        this.area = area;
        this.description = description;
    }

    public String getArea() { return area; }
    public String getDescription() { return description; }

    public void setArea(String area) { this.area = area; }
    public void setDescription(String description) { 
        this.description = description; 
    }

    @Override
    public String toString() {
        return area + " (" + description + ")";
    }
}
package com.rideshare.model;

public class Driver extends User {

    private String vehicleNumber;
    private String area;
    private boolean isAvailable;

    public Driver(String userId, String name, String phone, 
                  String vehicleNumber, String area) {
        super(userId, name, phone);
        this.vehicleNumber = vehicleNumber;
        this.area = area;
        this.isAvailable = true;
    }

    // Getters
    public String getVehicleNumber() { return vehicleNumber; }
    public String getArea() { return area; }
    public boolean isAvailable() { return isAvailable; }

    // Setters
    public void setAvailable(boolean available) { isAvailable = available; }
    public void setArea(String area) { this.area = area; }

    @Override
    public String getRole() {
        return "DRIVER";
    }

    @Override
    public String toString() {
        return super.toString() + 
               " | Vehicle: " + vehicleNumber + 
               " | Area: " + area + 
               " | Available: " + isAvailable;
    }
}
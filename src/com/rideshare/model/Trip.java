package com.rideshare.model;

public class Trip {

    private String tripId;
    private Rider rider;
    private Driver driver;
    private Location pickup;
    private Location destination;
    private double fare;
    private TripStatus status;
    private double distanceKm;

    public Trip(String tripId, Rider rider, Driver driver,
                Location pickup, Location destination, double distanceKm) {
        this.tripId = tripId;
        this.rider = rider;
        this.driver = driver;
        this.pickup = pickup;
        this.destination = destination;
        this.distanceKm = distanceKm;
        this.status = TripStatus.REQUESTED;
        this.fare = 0.0;
    }

    // Getters
    public String getTripId() { return tripId; }
    public Rider getRider() { return rider; }
    public Driver getDriver() { return driver; }
    public Location getPickup() { return pickup; }
    public Location getDestination() { return destination; }
    public double getFare() { return fare; }
    public TripStatus getStatus() { return status; }
    public double getDistanceKm() { return distanceKm; }

    // Setters
    public void setFare(double fare) { this.fare = fare; }
    public void setStatus(TripStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Trip ID : " + tripId +
               "\nRider   : " + rider.getName() +
               "\nDriver  : " + driver.getName() +
               "\nFrom    : " + pickup.getArea() +
               "\nTo      : " + destination.getArea() +
               "\nFare    : Rs." + fare +
               "\nStatus  : " + status;
    }
}
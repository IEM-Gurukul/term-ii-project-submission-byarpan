package com.rideshare.observer;

import com.rideshare.model.Trip;

public class DriverNotifier implements TripObserver {

    @Override
    public void update(Trip trip, String message) {
        System.out.println("Notification to Driver " + 
            trip.getDriver().getName() + ": " + message);
    }
}
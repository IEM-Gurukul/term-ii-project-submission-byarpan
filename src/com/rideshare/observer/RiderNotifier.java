package com.rideshare.observer;

import com.rideshare.model.Trip;

public class RiderNotifier implements TripObserver {

    @Override
    public void update(Trip trip, String message) {
        System.out.println("Notification to Rider " + 
            trip.getRider().getName() + ": " + message);
    }
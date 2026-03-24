package com.rideshare.observer;

import com.rideshare.model.Trip;

public interface TripObserver {

    void update(Trip trip, String message);
}
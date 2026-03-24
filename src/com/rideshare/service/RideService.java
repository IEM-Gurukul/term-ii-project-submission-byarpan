package com.rideshare.service;

import com.rideshare.model.*;
import com.rideshare.strategy.IFareStrategy;
import com.rideshare.observer.TripObserver;
import com.rideshare.dispatcher.DispatchService;
import com.rideshare.exception.InvalidLocationException;
import java.util.ArrayList;
import java.util.HashMap;

public class RideService {

    private DispatchService dispatchService;
    private ArrayList<TripObserver> observers;
    private HashMap<String, Trip> activeTrips;
    private ArrayList<Trip> tripHistory;
    private int tripCounter;

    public RideService(DispatchService dispatchService) {
        this.dispatchService = dispatchService;
        this.observers = new ArrayList<>();
        this.activeTrips = new HashMap<>();
        this.tripHistory = new ArrayList<>();
        this.tripCounter = 1;
    }

    public void addObserver(TripObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Trip trip, String message) {
        for (TripObserver observer : observers) {
            observer.update(trip, message);
        }
    }

    public Trip bookRide(Rider rider, Location pickup, 
                         Location destination, double distanceKm,
                         IFareStrategy fareStrategy) {

        if (pickup == null || destination == null) {
            throw new InvalidLocationException(
                "Pickup and destination cannot be empty."
            );
        }

        Driver driver = dispatchService.findNearestDriver(rider.getArea());
        driver.setAvailable(false);

        String tripId = "TRIP" + tripCounter++;
        Trip trip = new Trip(tripId, rider, driver, 
                             pickup, destination, distanceKm);

        double fare = fareStrategy.calculateFare(distanceKm);
        trip.setFare(fare);
        trip.setStatus(TripStatus.ACCEPTED);

        activeTrips.put(tripId, trip);

        notifyObservers(trip, "Your driver " + driver.getName() + 
                        " is on the way!");
        notifyObservers(trip, "New trip assigned to " + 
                        destination.getArea());

        trip.setStatus(TripStatus.COMPLETED);
        driver.setAvailable(true);
        activeTrips.remove(tripId);
        tripHistory.add(trip);

        return trip;
    }

    public ArrayList<Trip> getTripHistory() {
        return tripHistory;
    }
}

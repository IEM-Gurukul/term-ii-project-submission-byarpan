package com.rideshare.dispatcher;

import com.rideshare.model.Driver;
import com.rideshare.exception.DriverNotFoundException;
import java.util.ArrayList;

public class DispatchService {

    private ArrayList<Driver> availableDrivers;

    public DispatchService() {
        this.availableDrivers = new ArrayList<>();
    }

    public void addDriver(Driver driver) {
        availableDrivers.add(driver);
    }

    public Driver findNearestDriver(String riderArea) {
        for (Driver driver : availableDrivers) {
            if (driver.isAvailable()) {
                return driver;
            }
        }
        throw new DriverNotFoundException(
            "No driver available at the moment. Please try again later."
        );
    }

    public ArrayList<Driver> getAvailableDrivers() {
        return availableDrivers;
    }
}
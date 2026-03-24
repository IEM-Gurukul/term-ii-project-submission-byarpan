package com.rideshare.service;

import com.rideshare.dispatcher.DispatchService;
import com.rideshare.model.Driver;
import com.rideshare.model.Rider;
import com.rideshare.repository.DriverRepository;
import java.util.ArrayList;

public class UserService {

    private ArrayList<Rider> riders;
    private ArrayList<Driver> drivers;
    private DriverRepository driverRepository;
    private DispatchService dispatchService;
    private int driverCounter;
    private int riderCounter;

    public UserService(DispatchService dispatchService) {
        this.riders = new ArrayList<>();
        this.drivers = new ArrayList<>();
        this.driverRepository = new DriverRepository();
        this.dispatchService = dispatchService;
        this.driverCounter = 1;
        this.riderCounter = 1;
        loadDriversFromFile();
    }

    private void loadDriversFromFile() {
        ArrayList<Driver> loaded = driverRepository.loadAllDrivers();
        for (Driver driver : loaded) {
            drivers.add(driver);
            dispatchService.addDriver(driver);
        }
    }

    public Rider registerRider(String name, String phone, String area) {
        String userId = "R" + riderCounter++;
        Rider rider = new Rider(userId, name, phone, area);
        riders.add(rider);
        return rider;
    }

    public Driver registerDriver(String name, String phone,
                                  String vehicleNumber, String area) {
        String userId = "D" + driverCounter++;
        Driver driver = new Driver(userId, name, phone,
                                   vehicleNumber, area);
        drivers.add(driver);
        dispatchService.addDriver(driver);
        driverRepository.saveDriver(driver);
        return driver;
    }

    public ArrayList<Driver> getAllDrivers() {
        return drivers;
    }

    public ArrayList<Rider> getAllRiders() {
        return riders;
    }
}

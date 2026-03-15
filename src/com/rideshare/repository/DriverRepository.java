package com.rideshare.repository;

import com.rideshare.model.Driver;
import java.io.*;
import java.util.ArrayList;

public class DriverRepository {

    private static final String FILE_PATH = "data/drivers.txt";

    public void saveDriver(Driver driver) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_PATH, true))) {
            writer.write(driver.getUserId() + "," +
                        driver.getName() + "," +
                        driver.getPhone() + "," +
                        driver.getVehicleNumber() + "," +
                        driver.getArea());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving driver: " + e.getMessage());
        }
    }

    public ArrayList<Driver> loadAllDrivers() {
        ArrayList<Driver> drivers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        Driver driver = new Driver(
                            parts[0], parts[1], parts[2],
                            parts[3], parts[4]
                        );
                        drivers.add(driver);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading drivers: " + e.getMessage());
        }
        return drivers;
    }
}
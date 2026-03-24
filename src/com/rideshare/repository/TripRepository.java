package com.rideshare.repository;

import com.rideshare.model.Trip;
import java.io.*;
import java.util.ArrayList;

public class TripRepository {

    private static final String FILE_PATH = "data/trips.txt";

    public void saveTrip(Trip trip) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_PATH, true))) {
            writer.write(trip.getTripId() + "," +
                        trip.getRider().getName() + "," +
                        trip.getDriver().getName() + "," +
                        trip.getPickup().getArea() + "," +
                        trip.getDestination().getArea() + "," +
                        trip.getFare() + "," +
                        trip.getStatus());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving trip: " + e.getMessage());
        }
    }

    public ArrayList<String> loadAllTrips() {
        ArrayList<String> trips = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    trips.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading trips: " + e.getMessage());
        }
        return trips;
    }
}

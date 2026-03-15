package com.rideshare.strategy;

public class FlatFareStrategy implements IFareStrategy {

    private static final double FLAT_RATE = 50.0;

    @Override
    public double calculateFare(double distanceKm) {
        return FLAT_RATE;
    }

    @Override
    public String getStrategyName() {
        return "Flat Fare";
    }
}
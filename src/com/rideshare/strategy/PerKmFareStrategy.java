package com.rideshare.strategy;

public class PerKmFareStrategy implements IFareStrategy {

    private static final double RATE_PER_KM = 10.0;

    @Override
    public double calculateFare(double distanceKm) {
        return RATE_PER_KM * distanceKm;
    }

    @Override
    public String getStrategyName() {
        return "Per KM Fare";
    }
}


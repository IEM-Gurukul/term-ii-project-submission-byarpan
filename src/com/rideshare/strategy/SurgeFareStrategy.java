package com.rideshare.strategy;

public class SurgeFareStrategy implements IFareStrategy {

    private static final double RATE_PER_KM = 10.0;
    private static final double SURGE_MULTIPLIER = 2.0;

    @Override
    public double calculateFare(double distanceKm) {
        return RATE_PER_KM * distanceKm * SURGE_MULTIPLIER;
    }

    @Override
    public String getStrategyName() {
        return "Surge Fare";
    }
}
package com.rideshare.strategy;

public interface IFareStrategy {
    
    double calculateFare(double distanceKm);
    String getStrategyName();
}

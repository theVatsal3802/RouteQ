package com.vatsaladhiya.vatsal.routeq.routeq.strategies;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.RideRequest;

public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER = 10;

    double calculateFare(RideRequest rideRequest);
}

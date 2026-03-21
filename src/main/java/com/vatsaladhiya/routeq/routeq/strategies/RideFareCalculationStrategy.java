package com.vatsaladhiya.routeq.routeq.strategies;

import com.vatsaladhiya.routeq.routeq.entities.RideRequestEntity;

public interface RideFareCalculationStrategy {
    double RIDE_FARE_MULTIPLIER = 10;

    Double calculateFare(RideRequestEntity rideRequestEntity);
}

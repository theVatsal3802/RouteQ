package com.vatsaladhiya.vatsal.routeq.routeq.strategies.impl;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.RideRequest;
import com.vatsaladhiya.vatsal.routeq.routeq.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

public class RideFareCalculationSurgeRisingFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}

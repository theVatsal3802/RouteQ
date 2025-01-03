package com.vatsaladhiya.vatsal.routeq.routeq.strategies.impl;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.RideRequest;
import com.vatsaladhiya.vatsal.routeq.routeq.services.DistanceService;
import com.vatsaladhiya.vatsal.routeq.routeq.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareCalculationSurgeRisingFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;
    private static final double SURGE_FACTOR = 1.2;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.CalculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());
        return distance * RIDE_FARE_MULTIPLIER * SURGE_FACTOR;
    }
}

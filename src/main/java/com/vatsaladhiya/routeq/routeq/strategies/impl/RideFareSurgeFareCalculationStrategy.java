package com.vatsaladhiya.routeq.routeq.strategies.impl;

import com.vatsaladhiya.routeq.routeq.entities.RideRequestEntity;
import com.vatsaladhiya.routeq.routeq.services.DistanceService;
import com.vatsaladhiya.routeq.routeq.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgeFareCalculationStrategy implements RideFareCalculationStrategy {
    private static final double SURGE_FACTOR = 2;

    private final DistanceService distanceService;

    @Override
    public Double calculateFare(RideRequestEntity rideRequestEntity) {
        Double distance = distanceService.calculateDistance(
                rideRequestEntity.getPickupLocation(),
                rideRequestEntity.getDropOffLocation()
        );
        return distance * RIDE_FARE_MULTIPLIER * SURGE_FACTOR;
    }
}

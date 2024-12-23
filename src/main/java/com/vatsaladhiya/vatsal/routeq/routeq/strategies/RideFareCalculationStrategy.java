package com.vatsaladhiya.vatsal.routeq.routeq.strategies;

import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RideRequestDTO;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDTO rideRequestDTO);
}

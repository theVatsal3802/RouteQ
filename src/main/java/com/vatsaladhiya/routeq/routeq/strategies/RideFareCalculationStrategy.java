package com.vatsaladhiya.routeq.routeq.strategies;

import com.vatsaladhiya.routeq.routeq.dtos.RideRequestDto;

public interface RideFareCalculationStrategy {
    Double calculateFare(RideRequestDto rideRequestDto);
}

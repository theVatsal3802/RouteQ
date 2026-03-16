package com.vatsaladhiya.routeq.routeq.strategies;

import com.vatsaladhiya.routeq.routeq.dtos.RideRequestDto;
import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;

import java.util.List;

public interface DriverMatchingStrategy {
    List<DriverEntity> findMatchingDrivers(RideRequestDto rideRequestDto);
}

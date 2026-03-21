package com.vatsaladhiya.routeq.routeq.strategies;

import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideRequestEntity;

import java.util.List;

public interface DriverMatchingStrategy {
    List<DriverEntity> findMatchingDrivers(RideRequestEntity rideRequestEntity);
}

package com.vatsaladhiya.vatsal.routeq.routeq.strategies;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.Driver;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDrivers(RideRequest rideRequest);
}

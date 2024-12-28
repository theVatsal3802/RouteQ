package com.vatsaladhiya.vatsal.routeq.routeq.strategies.impl;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.Driver;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.RideRequest;
import com.vatsaladhiya.vatsal.routeq.routeq.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return List.of();
    }
}

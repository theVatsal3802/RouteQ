package com.vatsaladhiya.routeq.routeq.strategies.impl;

import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideRequestEntity;
import com.vatsaladhiya.routeq.routeq.repositories.DriverRepository;
import com.vatsaladhiya.routeq.routeq.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<DriverEntity> findMatchingDrivers(RideRequestEntity rideRequestEntity) {
        return driverRepository.findTenNearestDrivers(rideRequestEntity.getPickupLocation());
    }
}

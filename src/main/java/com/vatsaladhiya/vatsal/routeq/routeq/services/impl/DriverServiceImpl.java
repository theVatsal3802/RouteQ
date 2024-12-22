package com.vatsaladhiya.vatsal.routeq.routeq.services.impl;

import com.vatsaladhiya.vatsal.routeq.routeq.dtos.DriverDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RideDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RiderDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.services.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Override
    public RideDTO cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDTO startRide(Long rideId) {
        return null;
    }

    @Override
    public RideDTO endRide(Long rideId) {
        return null;
    }

    @Override
    public RideDTO acceptRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDTO rateARider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDTO getProfile() {
        return null;
    }

    @Override
    public List<RideDTO> getAllRides() {
        return List.of();
    }
}

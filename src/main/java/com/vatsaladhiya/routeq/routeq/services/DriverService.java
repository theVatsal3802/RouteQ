package com.vatsaladhiya.routeq.routeq.services;

import com.vatsaladhiya.routeq.routeq.dtos.DriverDto;
import com.vatsaladhiya.routeq.routeq.dtos.RideDto;
import com.vatsaladhiya.routeq.routeq.dtos.RiderDto;
import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;

import java.util.List;

public interface DriverService {
    RideDto cancelRide(Long rideId);
    RideDto startRide(Long rideId, String otp);
    RideDto endRide(Long rideId);
    RiderDto rateRider(Long rideId, Integer rating);
    RideDto acceptRide(Long rideId);
    DriverDto getProfile();
    List<RideDto> getAllRides();
    DriverEntity getCurrentDriver();
}

package com.vatsaladhiya.vatsal.routeq.routeq.services;

import com.vatsaladhiya.vatsal.routeq.routeq.dtos.DriverDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RideDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RiderDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.Driver;

import java.util.List;

public interface DriverService {
    RideDTO cancelRide(Long rideId);
    RideDTO startRide(Long rideId, String otp);
    RideDTO endRide(Long rideId);
    RideDTO acceptRide(Long rideRequestId);

    RiderDTO rateARider(Long rideId, Integer rating);

    DriverDTO getProfile();

    List<RideDTO> getAllRides();

    Driver getCurrentDriver();
}

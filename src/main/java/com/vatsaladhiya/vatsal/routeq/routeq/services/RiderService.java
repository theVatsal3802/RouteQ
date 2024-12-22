package com.vatsaladhiya.vatsal.routeq.routeq.services;

import com.vatsaladhiya.vatsal.routeq.routeq.dtos.DriverDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RideDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RideRequestDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RiderDTO;

import java.util.List;

public interface RiderService {
    RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);

    RideDTO cancelRide(Long rideId);

    DriverDTO rateADriver(Long rideId, Integer rating);

    RiderDTO getProfile();

    List<RideDTO> getAllRides();
}

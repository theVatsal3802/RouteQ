package com.vatsaladhiya.routeq.routeq.services;

import com.vatsaladhiya.routeq.routeq.dtos.DriverDto;
import com.vatsaladhiya.routeq.routeq.dtos.DriverRideDto;
import com.vatsaladhiya.routeq.routeq.dtos.RiderDto;
import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {
    DriverRideDto cancelRide(Long rideId);
    DriverRideDto startRide(Long rideId, String otp);
    DriverRideDto endRide(Long rideId);
    RiderDto rateRider(Long rideId, Integer rating);
    DriverRideDto acceptRide(Long rideId);
    DriverDto getProfile();
    Page<DriverRideDto> getAllRides(PageRequest pageRequest);
    DriverEntity getCurrentDriver();
    DriverEntity updateDriverAvailability(DriverEntity driver, boolean isAvailable);
}

package com.vatsaladhiya.routeq.routeq.services;

import com.vatsaladhiya.routeq.routeq.dtos.DriverDto;
import com.vatsaladhiya.routeq.routeq.dtos.RideRequestDto;
import com.vatsaladhiya.routeq.routeq.dtos.RiderDto;
import com.vatsaladhiya.routeq.routeq.dtos.RiderRideDto;
import com.vatsaladhiya.routeq.routeq.entities.RiderEntity;
import com.vatsaladhiya.routeq.routeq.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {
    RiderEntity createRider(UserEntity userEntity);
    RideRequestDto requestRide(RideRequestDto rideRequestDto);
    RiderRideDto cancelRide(Long rideId);
    DriverDto rateDriver(Long rideId, Integer rating);
    RiderDto getProfile();
    Page<RiderRideDto> getAllRides(PageRequest pageRequest);
    RiderEntity getCurrentRider();
}

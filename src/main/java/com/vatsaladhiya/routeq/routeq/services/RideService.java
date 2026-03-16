package com.vatsaladhiya.routeq.routeq.services;

import com.vatsaladhiya.routeq.routeq.dtos.RideRequestDto;
import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import com.vatsaladhiya.routeq.routeq.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;

public interface RideService {
    RideEntity getRideById(Long rideId);
    void matchWithDrivers(RideRequestDto rideRequestDto);
    RideEntity createNewRide(RideRequestDto rideRequestDto, DriverEntity driver);
    RideEntity updateRideStatus(Long rideId, RideStatus rideStatus);
    Page<RideEntity> getAllRidesOfRider(Long riderId, PageRequest pageRequest);
    Page<RideEntity> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}

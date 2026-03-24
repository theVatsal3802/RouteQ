package com.vatsaladhiya.routeq.routeq.services;

import com.vatsaladhiya.routeq.routeq.dtos.RideRequestDto;
import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideRequestEntity;
import com.vatsaladhiya.routeq.routeq.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {
    RideEntity getRideById(Long rideId);
    void matchWithDrivers(RideRequestDto rideRequestDto);
    RideEntity createNewRide(RideRequestEntity rideRequestEntity, DriverEntity driver);
    RideEntity updateRideStatus(RideEntity ride, RideStatus rideStatus);
    Page<RideEntity> getAllRidesOfRider(Long riderId, PageRequest pageRequest);
    Page<RideEntity> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}

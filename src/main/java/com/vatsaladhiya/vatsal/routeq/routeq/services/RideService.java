package com.vatsaladhiya.vatsal.routeq.routeq.services;

import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RideRequestDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.Driver;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.Ride;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {
    Ride getRideById(Long rideId);

    void matchWithDrivers(RideRequestDTO rideRequestDTO);

    Ride createNewRide(RideRequestDTO rideRequestDTO, Driver driver);

    Ride updateRideStatus(Long rideId, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}

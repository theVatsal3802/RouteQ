package com.vatsaladhiya.vatsal.routeq.routeq.services.impl;

import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RideRequestDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.Driver;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.Ride;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.RideRequest;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.enums.RideRequestStatus;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.enums.RideStatus;
import com.vatsaladhiya.vatsal.routeq.routeq.exceptions.ResourceNotFoundException;
import com.vatsaladhiya.vatsal.routeq.routeq.repositories.RideRepository;
import com.vatsaladhiya.vatsal.routeq.routeq.services.RideRequestService;
import com.vatsaladhiya.vatsal.routeq.routeq.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId).orElseThrow(() -> new ResourceNotFoundException("Ride not found with id: " + rideId));
    }

    @Override
    public void matchWithDrivers(RideRequestDTO rideRequestDTO) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOTP());
        ride.setId(null);
        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }

    private String generateRandomOTP() {
        Random random = new Random();
        int otp = random.nextInt(10000);
        return String.format("%04d", otp);
    }
}

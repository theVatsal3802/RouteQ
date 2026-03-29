package com.vatsaladhiya.routeq.routeq.services.impl;

import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideRequestEntity;
import com.vatsaladhiya.routeq.routeq.entities.RiderEntity;
import com.vatsaladhiya.routeq.routeq.enums.RideRequestStatus;
import com.vatsaladhiya.routeq.routeq.enums.RideStatus;
import com.vatsaladhiya.routeq.routeq.exceptions.ResourceNotFoundException;
import com.vatsaladhiya.routeq.routeq.repositories.RideRepository;
import com.vatsaladhiya.routeq.routeq.services.RideRequestService;
import com.vatsaladhiya.routeq.routeq.services.RideService;
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
    public RideEntity getRideById(Long rideId) {
        return rideRepository.findById(rideId).orElseThrow(
                () -> new ResourceNotFoundException("Ride not found with id: " + rideId)
        );
    }

    @Override
    public RideEntity createNewRide(RideRequestEntity rideRequestEntity, DriverEntity driver) {
        rideRequestEntity.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        RideEntity rideEntity = modelMapper.map(rideRequestEntity, RideEntity.class);
        rideEntity.setDriver(driver);
        rideEntity.setRideStatus(RideStatus.CONFIRMED);
        rideEntity.setOtp(getOtp());
        rideEntity.setId(null);
        rideRequestService.update(rideRequestEntity);
        return rideRepository.save(rideEntity);
    }

    @Override
    public RideEntity updateRideStatus(RideEntity ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<RideEntity> getAllRidesOfRider(RiderEntity rider, PageRequest pageRequest) {
        return rideRepository.findByRider(rider, pageRequest);
    }

    @Override
    public Page<RideEntity> getAllRidesOfDriver(DriverEntity driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver, pageRequest);
    }

    private String getOtp() {
        Random random = new Random();
        int otp = random.nextInt(10000);
        return String.format("%04d", otp);
    }
}

package com.vatsaladhiya.routeq.routeq.services.impl;

import com.vatsaladhiya.routeq.routeq.dtos.DriverDto;
import com.vatsaladhiya.routeq.routeq.dtos.RideDto;
import com.vatsaladhiya.routeq.routeq.dtos.RiderDto;
import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideRequestEntity;
import com.vatsaladhiya.routeq.routeq.enums.RideRequestStatus;
import com.vatsaladhiya.routeq.routeq.enums.RideStatus;
import com.vatsaladhiya.routeq.routeq.exceptions.*;
import com.vatsaladhiya.routeq.routeq.repositories.DriverRepository;
import com.vatsaladhiya.routeq.routeq.services.DriverService;
import com.vatsaladhiya.routeq.routeq.services.RideRequestService;
import com.vatsaladhiya.routeq.routeq.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final RideService rideService;
    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    @Transactional
    public RideDto startRide(Long rideId, String otp) {
        RideEntity ride = rideService.getRideById(rideId);
        DriverEntity driver = getCurrentDriver();
        if (!ride.getDriver().getId().equals(driver.getId())) {
            throw new IncorrectDriverException("Current driver not same as assigned driver");
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new InvalidRequestException("Ride Status is not CONFIRMED, status: " + ride.getRideStatus());
        }
        if (!ride.getOtp().equals(otp)) {
            throw new IncorrectOTPException("Incorrect OTP provided for ride");
        }
        ride.setStartedAt(LocalDateTime.now());
        RideEntity updatedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
        return modelMapper.map(updatedRide, RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverEntity getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(
                () -> new ResourceNotFoundException("Current driver not found")
        );
    }

    @Override
    @Transactional
    public RideDto acceptRide(Long rideId) {
        RideRequestEntity rideRequestEntity = rideRequestService.getRideRequestById(rideId);
        if (!rideRequestEntity.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new InvalidRequestException("Ride request already fulfilled or cancelled with id: " + rideId);
        }
        DriverEntity currentDriver = getCurrentDriver();
        if (!currentDriver.getAvailable()) {
            throw new ResourceUnavailableException("Current driver unavailable");
        }
        currentDriver.setAvailable(false);
        driverRepository.save(currentDriver);
        RideEntity newRideEntity = rideService.createNewRide(rideRequestEntity, currentDriver);
        return modelMapper.map(newRideEntity, RideDto.class);
    }

    @Override
    public DriverDto getProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllRides() {
        return List.of();
    }
}

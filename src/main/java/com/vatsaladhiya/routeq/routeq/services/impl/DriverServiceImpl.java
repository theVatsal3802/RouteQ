package com.vatsaladhiya.routeq.routeq.services.impl;

import com.vatsaladhiya.routeq.routeq.dtos.DriverDto;
import com.vatsaladhiya.routeq.routeq.dtos.DriverRideDto;
import com.vatsaladhiya.routeq.routeq.dtos.RiderDto;
import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideRequestEntity;
import com.vatsaladhiya.routeq.routeq.enums.RideRequestStatus;
import com.vatsaladhiya.routeq.routeq.enums.RideStatus;
import com.vatsaladhiya.routeq.routeq.exceptions.*;
import com.vatsaladhiya.routeq.routeq.repositories.DriverRepository;
import com.vatsaladhiya.routeq.routeq.services.DriverService;
import com.vatsaladhiya.routeq.routeq.services.PaymentService;
import com.vatsaladhiya.routeq.routeq.services.RideRequestService;
import com.vatsaladhiya.routeq.routeq.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final RideService rideService;
    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;

    @Override
    @Transactional
    public DriverRideDto cancelRide(Long rideId) {
        RideEntity ride = rideService.getRideById(rideId);
        DriverEntity driver = getCurrentDriver();

        if (!driver.equals(ride.getDriver())) {
            throw new IncorrectDriverException("Current driver not same as assigned driver");
        }

        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new InvalidRequestException("Ride already started, ended or cancelled, cannot cancel ride");
        }

        RideEntity updatedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        updateDriverAvailability(driver, true);
        return modelMapper.map(updatedRide, DriverRideDto.class);
    }

    @Override
    @Transactional
    public DriverRideDto startRide(Long rideId, String otp) {
        RideEntity ride = rideService.getRideById(rideId);
        DriverEntity driver = getCurrentDriver();
        if (!driver.equals(ride.getDriver())) {
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

        paymentService.createNewPayment(updatedRide);
        return modelMapper.map(updatedRide, DriverRideDto.class);
    }

    @Override
    @Transactional
    public DriverRideDto endRide(Long rideId) {
        DriverEntity driver = getCurrentDriver();
        RideEntity ride = rideService.getRideById(rideId);
        if (!driver.equals(ride.getDriver())) {
            throw new IncorrectDriverException("Current driver not same as assigned driver");
        }
        if (!ride.getRideStatus().equals(RideStatus.ONGOING)) {
            throw new InvalidRequestException("Ride Status is not ONGOING, status: " + ride.getRideStatus());
        }
        ride.setEndedAt(LocalDateTime.now());
        RideEntity updatedRide = rideService.updateRideStatus(ride, RideStatus.COMPLETED);
        updateDriverAvailability(driver, true);
        paymentService.processPayment(updatedRide);
        return modelMapper.map(updatedRide, DriverRideDto.class);
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
    public DriverRideDto acceptRide(Long rideId) {
        RideRequestEntity rideRequestEntity = rideRequestService.getRideRequestById(rideId);
        if (!rideRequestEntity.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new InvalidRequestException("Ride request already fulfilled or cancelled with id: " + rideId);
        }
        DriverEntity currentDriver = getCurrentDriver();
        if (!currentDriver.getAvailable()) {
            throw new ResourceUnavailableException("Current driver unavailable");
        }
        updateDriverAvailability(currentDriver, false);
        RideEntity newRideEntity = rideService.createNewRide(rideRequestEntity, currentDriver);
        return modelMapper.map(newRideEntity, DriverRideDto.class);
    }

    @Override
    public DriverDto getProfile() {
        DriverEntity driver = getCurrentDriver();
        return modelMapper.map(driver, DriverDto.class);
    }

    @Override
    public Page<DriverRideDto> getAllRides(PageRequest pageRequest) {
        DriverEntity driver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(driver, pageRequest).map(
                ride -> modelMapper.map(ride, DriverRideDto.class)
        );
    }

    @Override
    public DriverEntity updateDriverAvailability(DriverEntity driver, boolean isAvailable) {
        driver.setAvailable(isAvailable);
        return driverRepository.save(driver);
    }
}

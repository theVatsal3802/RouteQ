package com.vatsaladhiya.routeq.routeq.services.impl;

import com.vatsaladhiya.routeq.routeq.dtos.DriverDto;
import com.vatsaladhiya.routeq.routeq.dtos.RideRequestDto;
import com.vatsaladhiya.routeq.routeq.dtos.RiderDto;
import com.vatsaladhiya.routeq.routeq.dtos.RiderRideDto;
import com.vatsaladhiya.routeq.routeq.entities.*;
import com.vatsaladhiya.routeq.routeq.enums.RideRequestStatus;
import com.vatsaladhiya.routeq.routeq.enums.RideStatus;
import com.vatsaladhiya.routeq.routeq.exceptions.IncorrectRiderException;
import com.vatsaladhiya.routeq.routeq.exceptions.InvalidRequestException;
import com.vatsaladhiya.routeq.routeq.exceptions.ResourceNotFoundException;
import com.vatsaladhiya.routeq.routeq.repositories.RideRequestRepository;
import com.vatsaladhiya.routeq.routeq.repositories.RiderRepository;
import com.vatsaladhiya.routeq.routeq.services.DriverService;
import com.vatsaladhiya.routeq.routeq.services.RatingService;
import com.vatsaladhiya.routeq.routeq.services.RideService;
import com.vatsaladhiya.routeq.routeq.services.RiderService;
import com.vatsaladhiya.routeq.routeq.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RideRequestRepository rideRequestRepository;
    private final RatingService ratingService;

    @Override
    public RiderEntity createRider(UserEntity userEntity) {
        RiderEntity riderEntity = RiderEntity
                .builder()
                .user(userEntity)
                .rating(0.0)
                .build();
        return riderRepository.save(riderEntity);
    }

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RiderEntity rider = getCurrentRider();
        RideRequestEntity rideRequestEntity = modelMapper.map(rideRequestDto, RideRequestEntity.class);
        rideRequestEntity.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequestEntity.setRider(rider);

        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequestEntity);
        rideRequestEntity.setFare(fare);

        RideRequestEntity savedRideRequest = rideRequestRepository.save(rideRequestEntity);

        List<DriverEntity> drivers = rideStrategyManager
                .driverMatchingStrategy(rider.getRating())
                .findMatchingDrivers(rideRequestEntity);
        // TODO: Send notification to drivers
        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    @Transactional
    public RiderRideDto cancelRide(Long rideId) {
        RiderEntity rider = getCurrentRider();
        RideEntity ride = rideService.getRideById(rideId);

        if (!rider.equals(ride.getRider())) {
            throw new IncorrectRiderException("Rider mismatch with ride's rider, cannot cancel.");
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new InvalidRequestException("Ride either ongoing, ended or cancelled, cannot cancel.");
        }
        RideEntity updatedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(), true);
        return modelMapper.map(updatedRide, RiderRideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        RideEntity ride = rideService.getRideById(rideId);
        RiderEntity rider = getCurrentRider();
        if (!rider.equals(ride.getRider())) {
            throw new IncorrectRiderException("Current rider not same as booking rider");
        }
        if (!ride.getRideStatus().equals(RideStatus.COMPLETED)) {
            throw new InvalidRequestException("Ride Status is not COMPLETED, status: " + ride.getRideStatus());
        }
        return modelMapper.map(ratingService.rateDriver(ride, rating), DriverDto.class);
    }

    @Override
    public RiderDto getProfile() {
        RiderEntity rider = getCurrentRider();
        return modelMapper.map(rider, RiderDto.class);
    }

    @Override
    public Page<RiderRideDto> getAllRides(PageRequest pageRequest) {
        RiderEntity rider = getCurrentRider();
        return rideService.getAllRidesOfRider(rider, pageRequest).map(
                ride -> modelMapper.map(ride, RiderRideDto.class)
        );
    }

    @Override
    public RiderEntity getCurrentRider() {
        return riderRepository.findById(1L).orElseThrow(() ->
                new ResourceNotFoundException("Rider not found with id: 1")
        );
    }
}

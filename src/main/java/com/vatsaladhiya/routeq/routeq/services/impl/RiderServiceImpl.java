package com.vatsaladhiya.routeq.routeq.services.impl;

import com.vatsaladhiya.routeq.routeq.dtos.DriverDto;
import com.vatsaladhiya.routeq.routeq.dtos.RideDto;
import com.vatsaladhiya.routeq.routeq.dtos.RideRequestDto;
import com.vatsaladhiya.routeq.routeq.dtos.RiderDto;
import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideRequestEntity;
import com.vatsaladhiya.routeq.routeq.entities.RiderEntity;
import com.vatsaladhiya.routeq.routeq.entities.UserEntity;
import com.vatsaladhiya.routeq.routeq.enums.RideRequestStatus;
import com.vatsaladhiya.routeq.routeq.exceptions.ResourceNotFoundException;
import com.vatsaladhiya.routeq.routeq.repositories.RideRequestRepository;
import com.vatsaladhiya.routeq.routeq.repositories.RiderRepository;
import com.vatsaladhiya.routeq.routeq.services.RiderService;
import com.vatsaladhiya.routeq.routeq.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RiderRepository riderRepository;
    private final RideRequestRepository rideRequestRepository;

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

        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllRides() {
        return List.of();
    }

    @Override
    public RiderEntity getCurrentRider() {
        return riderRepository.findById(1L).orElseThrow(() ->
                new ResourceNotFoundException("Rider not found with id: 1")
        );
    }
}

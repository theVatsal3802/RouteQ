package com.vatsaladhiya.routeq.routeq.services.impl;

import com.vatsaladhiya.routeq.routeq.dtos.DriverDto;
import com.vatsaladhiya.routeq.routeq.dtos.RideDto;
import com.vatsaladhiya.routeq.routeq.dtos.RideRequestDto;
import com.vatsaladhiya.routeq.routeq.dtos.RiderDto;
import com.vatsaladhiya.routeq.routeq.entities.RideRequestEntity;
import com.vatsaladhiya.routeq.routeq.entities.RiderEntity;
import com.vatsaladhiya.routeq.routeq.entities.UserEntity;
import com.vatsaladhiya.routeq.routeq.enums.RideRequestStatus;
import com.vatsaladhiya.routeq.routeq.repositories.RideRequestRepository;
import com.vatsaladhiya.routeq.routeq.repositories.RiderRepository;
import com.vatsaladhiya.routeq.routeq.services.RiderService;
import com.vatsaladhiya.routeq.routeq.strategies.DriverMatchingStrategy;
import com.vatsaladhiya.routeq.routeq.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideFareCalculationStrategy rideFareCalculationStrategy;
    private final RiderRepository riderRepository;
    private final DriverMatchingStrategy driverMatchingStrategy;
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
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequestEntity rideRequestEntity = modelMapper.map(rideRequestDto, RideRequestEntity.class);
        rideRequestEntity.setRideRequestStatus(RideRequestStatus.PENDING);
        Double fare = rideFareCalculationStrategy.calculateFare(rideRequestEntity);
        rideRequestEntity.setFare(fare);

        RideRequestEntity savedRideRequest = rideRequestRepository.save(rideRequestEntity);

        driverMatchingStrategy.findMatchingDrivers(rideRequestEntity);

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
}

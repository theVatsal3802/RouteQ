package com.vatsaladhiya.vatsal.routeq.routeq.services.impl;

import com.vatsaladhiya.vatsal.routeq.routeq.dtos.DriverDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RideDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RideRequestDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.RiderDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.Driver;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.RideRequest;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.Rider;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.User;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.enums.RideRequestStatus;
import com.vatsaladhiya.vatsal.routeq.routeq.repositories.RideRequestRepository;
import com.vatsaladhiya.vatsal.routeq.routeq.repositories.RiderRepository;
import com.vatsaladhiya.vatsal.routeq.routeq.services.RiderService;
import com.vatsaladhiya.vatsal.routeq.routeq.strategies.DriverMatchingStrategy;
import com.vatsaladhiya.vatsal.routeq.routeq.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideFareCalculationStrategy rideFareCalculationStrategy;
    private final DriverMatchingStrategy driverMatchingStrategy;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    @Override
    public Rider createRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
        return riderRepository.save(rider);
    }

    @Override
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        RideRequest rideRequest = modelMapper.map(rideRequestDTO, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        Double fare = rideFareCalculationStrategy.calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        driverMatchingStrategy.findMatchingDrivers(rideRequest);


        return modelMapper.map(savedRideRequest, RideRequestDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDTO rateADriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDTO getProfile() {
        return null;
    }

    @Override
    public List<RideDTO> getAllRides() {
        return List.of();
    }
}

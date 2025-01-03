package com.vatsaladhiya.vatsal.routeq.routeq.services.impl;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.RideRequest;
import com.vatsaladhiya.vatsal.routeq.routeq.exceptions.ResourceNotFoundException;
import com.vatsaladhiya.vatsal.routeq.routeq.repositories.RideRequestRepository;
import com.vatsaladhiya.vatsal.routeq.routeq.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId).orElseThrow(() -> new ResourceNotFoundException("Ride Request not found with id: " + rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id: " + rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}

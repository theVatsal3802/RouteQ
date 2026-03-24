package com.vatsaladhiya.routeq.routeq.services.impl;

import com.vatsaladhiya.routeq.routeq.entities.RideRequestEntity;
import com.vatsaladhiya.routeq.routeq.exceptions.ResourceNotFoundException;
import com.vatsaladhiya.routeq.routeq.repositories.RideRequestRepository;
import com.vatsaladhiya.routeq.routeq.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public void update(RideRequestEntity rideRequestEntity) {
        rideRequestRepository.findById(rideRequestEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Ride Request not found with id: " + rideRequestEntity.getId()));
        rideRequestRepository.save(rideRequestEntity);
    }

    @Override
    public RideRequestEntity getRideRequestById(Long rideRequestId) {
        return rideRequestRepository
                .findById(rideRequestId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Ride Request does not exist with id: " + rideRequestId
                        )
                );
    }
}

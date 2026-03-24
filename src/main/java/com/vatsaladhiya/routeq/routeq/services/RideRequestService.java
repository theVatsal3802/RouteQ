package com.vatsaladhiya.routeq.routeq.services;

import com.vatsaladhiya.routeq.routeq.entities.RideRequestEntity;

public interface RideRequestService {
    RideRequestEntity getRideRequestById(Long rideRequestId);

    void update(RideRequestEntity rideRequestEntity);
}

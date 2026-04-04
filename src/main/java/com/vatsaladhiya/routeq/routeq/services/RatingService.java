package com.vatsaladhiya.routeq.routeq.services;

import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import com.vatsaladhiya.routeq.routeq.entities.RiderEntity;

public interface RatingService {
    DriverEntity rateDriver(RideEntity ride, Integer rating);
    RiderEntity rateRider(RideEntity ride, Integer rating);
    void createNewRating(RideEntity ride);
}

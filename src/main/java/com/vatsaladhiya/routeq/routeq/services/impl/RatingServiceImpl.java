package com.vatsaladhiya.routeq.routeq.services.impl;

import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.RatingEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import com.vatsaladhiya.routeq.routeq.entities.RiderEntity;
import com.vatsaladhiya.routeq.routeq.exceptions.ResourceNotFoundException;
import com.vatsaladhiya.routeq.routeq.exceptions.RuntimeConflictException;
import com.vatsaladhiya.routeq.routeq.repositories.DriverRepository;
import com.vatsaladhiya.routeq.routeq.repositories.RatingRepository;
import com.vatsaladhiya.routeq.routeq.repositories.RiderRepository;
import com.vatsaladhiya.routeq.routeq.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;

    @Override
    public DriverEntity rateDriver(RideEntity ride, Integer rating) {
        RatingEntity ratingEntity = findByRide(ride);

        if (ratingEntity.getDriverRating() != null) {
            throw new RuntimeConflictException("Driver already rated");
        }
        ratingEntity.setDriverRating(rating);
        ratingRepository.save(ratingEntity);

        DriverEntity driver = ride.getDriver();
        Double newRating = ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(RatingEntity::getDriverRating)
                .average()
                .orElse(0.0);
        driver.setRating(newRating);
        return driverRepository.save(driver);
    }

    @Override
    public RiderEntity rateRider(RideEntity ride, Integer rating) {
        RatingEntity ratingEntity = findByRide(ride);

        if (ratingEntity.getRiderRating() != null) {
            throw new RuntimeConflictException("Rider already rated");
        }
        ratingEntity.setRiderRating(rating);
        ratingRepository.save(ratingEntity);

        RiderEntity rider = ride.getRider();
        Double newRating = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(RatingEntity::getRiderRating)
                .average()
                .orElse(0.0);
        rider.setRating(newRating);
        return riderRepository.save(rider);
    }

    @Override
    public void createNewRating(RideEntity ride) {
        RatingEntity rating = RatingEntity.builder()
                .ride(ride)
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .build();
        ratingRepository.save(rating);
    }

    private RatingEntity findByRide(RideEntity ride) {
        return ratingRepository.findByRide(ride).orElseThrow(
                () -> new ResourceNotFoundException("Rating not found with ride id: " + ride.getId())
        );
    }
}

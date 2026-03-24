package com.vatsaladhiya.routeq.routeq.strategies;

import com.vatsaladhiya.routeq.routeq.strategies.impl.DriverMatchingHighestRatedDriversStrategy;
import com.vatsaladhiya.routeq.routeq.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.vatsaladhiya.routeq.routeq.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.vatsaladhiya.routeq.routeq.strategies.impl.RideFareSurgeFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final DriverMatchingHighestRatedDriversStrategy driverMatchingHighestRatedDriversStrategy;
    private final DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;
    private final RideFareDefaultFareCalculationStrategy rideFareDefaultFareCalculationStrategy;
    private final RideFareSurgeFareCalculationStrategy rideFareSurgeFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
        if (riderRating >= 4.8) {
            return driverMatchingHighestRatedDriversStrategy;
        } else {
            return driverMatchingNearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy() {
        LocalTime surgeTimeStart = LocalTime.of(18, 0);
        LocalTime surgeTimeEnd = LocalTime.of(21, 0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeTimeStart) && currentTime.isBefore(surgeTimeEnd);

        if (isSurgeTime) {
            return rideFareSurgeFareCalculationStrategy;
        } else {
            return rideFareDefaultFareCalculationStrategy;
        }
    }
}

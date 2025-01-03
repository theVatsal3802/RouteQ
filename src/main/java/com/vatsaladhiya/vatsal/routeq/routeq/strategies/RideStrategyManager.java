package com.vatsaladhiya.vatsal.routeq.routeq.strategies;

import com.vatsaladhiya.vatsal.routeq.routeq.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.vatsaladhiya.vatsal.routeq.routeq.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.vatsaladhiya.vatsal.routeq.routeq.strategies.impl.RideFareCalculationDefaultFareCalculationStrategy;
import com.vatsaladhiya.vatsal.routeq.routeq.strategies.impl.RideFareCalculationSurgeRisingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;
    private final RideFareCalculationDefaultFareCalculationStrategy rideFareCalculationDefaultFareCalculationStrategy;
    private final RideFareCalculationSurgeRisingFareCalculationStrategy rideFareCalculationSurgeRisingFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
        if (riderRating >= 4.8) {
            return driverMatchingHighestRatedDriverStrategy;
        }
        return driverMatchingNearestDriverStrategy;
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy() {
        LocalTime surgeStartTime = LocalTime.of(18, 0);
        LocalTime surgeEndTime = LocalTime.of(21, 0);

        LocalTime currentTime = LocalTime.now();
        if (currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime)) {
            return rideFareCalculationSurgeRisingFareCalculationStrategy;
        }
        return rideFareCalculationDefaultFareCalculationStrategy;
    }
}

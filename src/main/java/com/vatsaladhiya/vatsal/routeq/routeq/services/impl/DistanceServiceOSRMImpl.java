package com.vatsaladhiya.vatsal.routeq.routeq.services.impl;

import com.vatsaladhiya.vatsal.routeq.routeq.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double CalculateDistance(Point src, Point dest) {
        return 0;
    }
}

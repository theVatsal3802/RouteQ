package com.vatsaladhiya.vatsal.routeq.routeq.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {
    double CalculateDistance(Point src, Point dest);
}

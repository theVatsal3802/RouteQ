package com.vatsaladhiya.routeq.routeq.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {
    Double calculateDistance(Point src, Point dest);
}

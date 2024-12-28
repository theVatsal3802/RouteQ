package com.vatsaladhiya.vatsal.routeq.routeq.dtos;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.enums.PaymentMethod;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.enums.RideStatus;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDTO {
    private Long id;
    private Point pickupLocation;
    private Point dropOffLocation;
    private LocalDateTime createdTime;
    private RiderDTO rider;
    private DriverDTO driver;
    private PaymentMethod paymentMethod;
    private RideStatus rideRequestStatus;
    private Double fare;
    private String otp;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}

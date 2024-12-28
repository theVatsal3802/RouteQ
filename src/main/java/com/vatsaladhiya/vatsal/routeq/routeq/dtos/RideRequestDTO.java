package com.vatsaladhiya.vatsal.routeq.routeq.dtos;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.enums.PaymentMethod;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RideRequestDTO {
    private Long id;

    private PointDTO pickupLocation;

    private PointDTO dropOffLocation;

    private LocalDateTime requestedTime;

    private RiderDTO rider;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;
}

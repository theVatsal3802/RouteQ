package com.vatsaladhiya.routeq.routeq.dtos;

import com.vatsaladhiya.routeq.routeq.enums.PaymentMethod;
import com.vatsaladhiya.routeq.routeq.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {
    private Long id;

    private PointDto pickupLocation;

    private PointDto dropOffLocation;

    private LocalDateTime requestedTime;

    private RiderDto rider;

    private PaymentMethod paymentMethod;
    private Double fare;

    private RideRequestStatus rideRequestStatus;

}

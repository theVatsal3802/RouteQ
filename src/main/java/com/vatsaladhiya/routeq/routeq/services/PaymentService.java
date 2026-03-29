package com.vatsaladhiya.routeq.routeq.services;

import com.vatsaladhiya.routeq.routeq.entities.PaymentEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import com.vatsaladhiya.routeq.routeq.enums.PaymentStatus;

public interface PaymentService {
    void processPayment(RideEntity ride);
    PaymentEntity createNewPayment(RideEntity ride);
}

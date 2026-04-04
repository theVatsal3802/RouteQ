package com.vatsaladhiya.routeq.routeq.services;

import com.vatsaladhiya.routeq.routeq.entities.PaymentEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideEntity;

public interface PaymentService {
    void processPayment(RideEntity ride);
    PaymentEntity createNewPayment(RideEntity ride);
}

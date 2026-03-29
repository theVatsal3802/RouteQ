package com.vatsaladhiya.routeq.routeq.strategies;

import com.vatsaladhiya.routeq.routeq.entities.PaymentEntity;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION = 0.3;

    void processPayment(PaymentEntity payment);
}

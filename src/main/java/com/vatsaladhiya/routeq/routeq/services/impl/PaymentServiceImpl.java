package com.vatsaladhiya.routeq.routeq.services.impl;

import com.vatsaladhiya.routeq.routeq.entities.PaymentEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import com.vatsaladhiya.routeq.routeq.enums.PaymentStatus;
import com.vatsaladhiya.routeq.routeq.exceptions.ResourceNotFoundException;
import com.vatsaladhiya.routeq.routeq.repositories.PaymentRepository;
import com.vatsaladhiya.routeq.routeq.services.PaymentService;
import com.vatsaladhiya.routeq.routeq.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(RideEntity ride) {
        PaymentEntity paymentEntity = paymentRepository.findByRide(ride).orElseThrow(
                () -> new ResourceNotFoundException("Payment object not found for ride with id: " + ride.getId())
        );
        paymentStrategyManager.paymentStrategy(paymentEntity.getPaymentMethod()).processPayment(paymentEntity);
    }

    @Override
    public PaymentEntity createNewPayment(RideEntity ride) {
        PaymentEntity payment = PaymentEntity.builder()
                .paymentMethod(ride.getPaymentMethod())
                .paymentStatus(PaymentStatus.PENDING)
                .amount(ride.getFare())
                .ride(ride)
                .build();
        return paymentRepository.save(payment);
    }
}

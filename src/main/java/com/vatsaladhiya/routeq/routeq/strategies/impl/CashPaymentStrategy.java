package com.vatsaladhiya.routeq.routeq.strategies.impl;

import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.PaymentEntity;
import com.vatsaladhiya.routeq.routeq.enums.PaymentStatus;
import com.vatsaladhiya.routeq.routeq.enums.TransactionMethod;
import com.vatsaladhiya.routeq.routeq.repositories.PaymentRepository;
import com.vatsaladhiya.routeq.routeq.services.WalletService;
import com.vatsaladhiya.routeq.routeq.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(PaymentEntity payment) {
        DriverEntity driver = payment.getRide().getDriver();
        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;
        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, null, payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}

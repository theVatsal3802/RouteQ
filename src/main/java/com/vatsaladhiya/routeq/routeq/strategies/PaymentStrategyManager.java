package com.vatsaladhiya.routeq.routeq.strategies;

import com.vatsaladhiya.routeq.routeq.enums.PaymentMethod;
import com.vatsaladhiya.routeq.routeq.strategies.impl.CashPaymentStrategy;
import com.vatsaladhiya.routeq.routeq.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod method) {
        return switch (method) {
            case CASH -> cashPaymentStrategy;
            case WALLET -> walletPaymentStrategy;
        };
    }
}

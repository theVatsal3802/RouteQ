package com.vatsaladhiya.routeq.routeq.services;

import com.vatsaladhiya.routeq.routeq.entities.WalletTransactionEntity;

public interface WalletTransactionService {
    void createNewWalletTransaction(WalletTransactionEntity walletTransaction);
}

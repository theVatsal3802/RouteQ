package com.vatsaladhiya.routeq.routeq.services;

import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import com.vatsaladhiya.routeq.routeq.entities.UserEntity;
import com.vatsaladhiya.routeq.routeq.entities.WalletEntity;
import com.vatsaladhiya.routeq.routeq.enums.TransactionMethod;

public interface WalletService {
    WalletEntity addMoneyToWallet(UserEntity user, Double amount, String transactionId, RideEntity ride, TransactionMethod method);
    void withdrawMoneyFromWallet();
    WalletEntity findWalletById(Long walletId);
    WalletEntity createNewWallet(UserEntity user);
    WalletEntity findWalletByUser(UserEntity user);
    WalletEntity deductMoneyFromWallet(UserEntity user, Double amount, String transactionId, RideEntity ride, TransactionMethod method);
}

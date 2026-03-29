package com.vatsaladhiya.routeq.routeq.services.impl;

import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import com.vatsaladhiya.routeq.routeq.entities.UserEntity;
import com.vatsaladhiya.routeq.routeq.entities.WalletEntity;
import com.vatsaladhiya.routeq.routeq.entities.WalletTransactionEntity;
import com.vatsaladhiya.routeq.routeq.enums.TransactionMethod;
import com.vatsaladhiya.routeq.routeq.enums.TransactionType;
import com.vatsaladhiya.routeq.routeq.exceptions.ResourceNotFoundException;
import com.vatsaladhiya.routeq.routeq.repositories.WalletRepository;
import com.vatsaladhiya.routeq.routeq.services.WalletService;
import com.vatsaladhiya.routeq.routeq.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;

    @Override
    @Transactional
    public WalletEntity deductMoneyFromWallet(UserEntity user, Double amount, String transactionId, RideEntity ride, TransactionMethod method) {
        WalletEntity wallet = findWalletByUser(user);
        wallet.setBalance(wallet.getBalance() - amount);
        WalletTransactionEntity walletTransaction = WalletTransactionEntity.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(method)
                .amount(amount)
                .build();
//        walletTransactionService.createNewWalletTransaction(walletTransaction);
        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public WalletEntity addMoneyToWallet(UserEntity user, Double amount, String transactionId, RideEntity ride, TransactionMethod method) {
        WalletEntity wallet = findWalletByUser(user);
        Double balance = wallet.getBalance();
        balance += amount;
        wallet.setBalance(balance);
        WalletTransactionEntity walletTransaction = WalletTransactionEntity.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(method)
                .amount(amount)
                .build();
        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawMoneyFromWallet() {

    }

    @Override
    public WalletEntity findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(
                () -> new ResourceNotFoundException("Wallet not found with id: " + walletId)
        );
    }

    @Override
    public WalletEntity createNewWallet(UserEntity user) {
        WalletEntity wallet = new WalletEntity();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public WalletEntity findWalletByUser(UserEntity user) {
        return walletRepository.findByUser(user).orElseThrow(
                () -> new ResourceNotFoundException("Wallet not found for user with id: " + user.getId())
        );
    }
}

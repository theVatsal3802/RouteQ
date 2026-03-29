package com.vatsaladhiya.routeq.routeq.repositories;

import com.vatsaladhiya.routeq.routeq.entities.WalletTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransactionEntity, Long> {
}

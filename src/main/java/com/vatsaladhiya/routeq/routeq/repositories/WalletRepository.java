package com.vatsaladhiya.routeq.routeq.repositories;

import com.vatsaladhiya.routeq.routeq.entities.UserEntity;
import com.vatsaladhiya.routeq.routeq.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Long> {
    Optional<WalletEntity> findByUser(UserEntity user);
}

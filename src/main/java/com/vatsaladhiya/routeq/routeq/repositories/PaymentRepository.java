package com.vatsaladhiya.routeq.routeq.repositories;

import com.vatsaladhiya.routeq.routeq.entities.PaymentEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    Optional<PaymentEntity> findByRide(RideEntity ride);
}

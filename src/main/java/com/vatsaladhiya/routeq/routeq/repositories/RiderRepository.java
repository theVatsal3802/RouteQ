package com.vatsaladhiya.routeq.routeq.repositories;

import com.vatsaladhiya.routeq.routeq.entities.RiderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<RiderEntity, Long> {
    Optional<RiderEntity> findByUser(User user);
}

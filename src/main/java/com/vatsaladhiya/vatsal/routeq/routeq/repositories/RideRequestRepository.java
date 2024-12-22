package com.vatsaladhiya.vatsal.routeq.routeq.repositories;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
}

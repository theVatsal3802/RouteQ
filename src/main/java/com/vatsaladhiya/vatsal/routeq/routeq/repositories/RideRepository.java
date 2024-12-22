package com.vatsaladhiya.vatsal.routeq.routeq.repositories;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
}

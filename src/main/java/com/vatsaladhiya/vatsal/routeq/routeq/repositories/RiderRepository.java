package com.vatsaladhiya.vatsal.routeq.routeq.repositories;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
}

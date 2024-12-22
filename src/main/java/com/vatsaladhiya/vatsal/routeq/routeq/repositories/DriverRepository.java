package com.vatsaladhiya.vatsal.routeq.routeq.repositories;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}

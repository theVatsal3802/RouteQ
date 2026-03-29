package com.vatsaladhiya.routeq.routeq.repositories;

import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.RideEntity;
import com.vatsaladhiya.routeq.routeq.entities.RiderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<RideEntity, Long> {
    Page<RideEntity> findByDriver(DriverEntity driver, Pageable pageRequest);

    Page<RideEntity> findByRider(RiderEntity rider, Pageable pageRequest);
}

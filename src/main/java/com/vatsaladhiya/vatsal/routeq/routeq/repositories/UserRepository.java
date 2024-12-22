package com.vatsaladhiya.vatsal.routeq.routeq.repositories;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

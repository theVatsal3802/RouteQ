package com.vatsaladhiya.routeq.routeq.services;

import com.vatsaladhiya.routeq.routeq.dtos.DriverDto;
import com.vatsaladhiya.routeq.routeq.dtos.SignupDto;
import com.vatsaladhiya.routeq.routeq.dtos.UserDto;

public interface AuthService {
    String[] login(String email, String password);
    UserDto signup(SignupDto signupDto);
    DriverDto onboardNewDriver(Long userId, String vehicleId);
}

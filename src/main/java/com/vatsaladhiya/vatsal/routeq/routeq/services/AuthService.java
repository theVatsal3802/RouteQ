package com.vatsaladhiya.vatsal.routeq.routeq.services;

import com.vatsaladhiya.vatsal.routeq.routeq.dtos.DriverDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.SignupDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.UserDTO;

public interface AuthService {
    String login(String email, String password);

    UserDTO signup(SignupDTO signupDTO);

    DriverDTO onboardNewDriver(Long userId);
}

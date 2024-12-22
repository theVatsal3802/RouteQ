package com.vatsaladhiya.vatsal.routeq.routeq.services.impl;


import com.vatsaladhiya.vatsal.routeq.routeq.dtos.DriverDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.SignupDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.UserDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDTO signup(SignupDTO signupDTO) {
        return null;
    }

    @Override
    public DriverDTO onboardNewDriver(Long userId) {
        return null;
    }
}

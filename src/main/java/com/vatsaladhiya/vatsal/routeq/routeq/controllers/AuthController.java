package com.vatsaladhiya.vatsal.routeq.routeq.controllers;

import com.vatsaladhiya.vatsal.routeq.routeq.dtos.SignupDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.UserDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/signup")
    UserDTO signupNewUser(@RequestBody SignupDTO signupDTO) {
        return authService.signup(signupDTO);
    }
}

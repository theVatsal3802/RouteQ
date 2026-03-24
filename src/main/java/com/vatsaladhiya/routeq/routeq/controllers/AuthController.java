package com.vatsaladhiya.routeq.routeq.controllers;

import com.vatsaladhiya.routeq.routeq.dtos.SignupDto;
import com.vatsaladhiya.routeq.routeq.dtos.UserDto;
import com.vatsaladhiya.routeq.routeq.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto) {
        return ResponseEntity.ok(authService.signup(signupDto));
    }
}

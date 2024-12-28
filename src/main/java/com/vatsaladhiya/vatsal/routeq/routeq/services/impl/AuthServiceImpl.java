package com.vatsaladhiya.vatsal.routeq.routeq.services.impl;


import com.vatsaladhiya.vatsal.routeq.routeq.dtos.DriverDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.SignupDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.dtos.UserDTO;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.User;
import com.vatsaladhiya.vatsal.routeq.routeq.entities.enums.Role;
import com.vatsaladhiya.vatsal.routeq.routeq.exceptions.RuntimeConflictException;
import com.vatsaladhiya.vatsal.routeq.routeq.repositories.UserRepository;
import com.vatsaladhiya.vatsal.routeq.routeq.services.AuthService;
import com.vatsaladhiya.vatsal.routeq.routeq.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;


    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDTO signup(SignupDTO signupDTO) {
        User alreadyExists = userRepository.findByEmail(signupDTO.getEmail()).orElse(null);
        if (alreadyExists != null) {
            throw new RuntimeConflictException("Cannot signup, user already exists with email: " + signupDTO.getEmail());
        }
        User user = modelMapper.map(signupDTO, User.class);
        user.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(user);
        riderService.createRider(savedUser);
        // TODO: Add wallet related services

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public DriverDTO onboardNewDriver(Long userId) {
        return null;
    }
}

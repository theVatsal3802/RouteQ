package com.vatsaladhiya.routeq.routeq.services.impl;

import com.vatsaladhiya.routeq.routeq.dtos.DriverDto;
import com.vatsaladhiya.routeq.routeq.dtos.SignupDto;
import com.vatsaladhiya.routeq.routeq.dtos.UserDto;
import com.vatsaladhiya.routeq.routeq.entities.RiderEntity;
import com.vatsaladhiya.routeq.routeq.entities.UserEntity;
import com.vatsaladhiya.routeq.routeq.enums.Role;
import com.vatsaladhiya.routeq.routeq.exceptions.RuntimeConflictException;
import com.vatsaladhiya.routeq.routeq.repositories.UserRepository;
import com.vatsaladhiya.routeq.routeq.services.AuthService;
import com.vatsaladhiya.routeq.routeq.services.RiderService;
import com.vatsaladhiya.routeq.routeq.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        UserEntity existingUser = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if (existingUser != null) {
            throw new RuntimeConflictException("Cannot signup, User already exists with email " + signupDto.getEmail());
        }
        UserEntity userEntity = modelMapper.map(signupDto, UserEntity.class);
        userEntity.setRoles(Set.of(Role.RIDER));
        UserEntity savedUser = userRepository.save(userEntity);
        // Create User related Entities (Rider and Wallet)
        RiderEntity rider = riderService.createRider(userEntity);
        walletService.createNewWallet(savedUser);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}

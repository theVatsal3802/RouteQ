package com.vatsaladhiya.routeq.routeq.services.impl;

import com.vatsaladhiya.routeq.routeq.dtos.DriverDto;
import com.vatsaladhiya.routeq.routeq.dtos.SignupDto;
import com.vatsaladhiya.routeq.routeq.dtos.UserDto;
import com.vatsaladhiya.routeq.routeq.entities.DriverEntity;
import com.vatsaladhiya.routeq.routeq.entities.RiderEntity;
import com.vatsaladhiya.routeq.routeq.entities.UserEntity;
import com.vatsaladhiya.routeq.routeq.enums.Role;
import com.vatsaladhiya.routeq.routeq.exceptions.ResourceNotFoundException;
import com.vatsaladhiya.routeq.routeq.exceptions.RuntimeConflictException;
import com.vatsaladhiya.routeq.routeq.filters.JWTService;
import com.vatsaladhiya.routeq.routeq.repositories.UserRepository;
import com.vatsaladhiya.routeq.routeq.services.AuthService;
import com.vatsaladhiya.routeq.routeq.services.DriverService;
import com.vatsaladhiya.routeq.routeq.services.RiderService;
import com.vatsaladhiya.routeq.routeq.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String[] login(String email, String password) {
        String[] tokens = new String[2];
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserEntity user = (UserEntity) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        tokens[0] = accessToken;
        tokens[1] = refreshToken;
        return tokens;
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
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserEntity savedUser = userRepository.save(userEntity);
        // Create User related Entities (Rider and Wallet)
        RiderEntity rider = riderService.createRider(userEntity);
        walletService.createNewWallet(savedUser);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    @Transactional
    public DriverDto onboardNewDriver(Long userId, String vehicleId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + userId)
        );
        if (user.getRoles().contains(Role.DRIVER)) {
            throw new RuntimeConflictException("User with id: " + userId + " is already a driver");
        }
        DriverEntity newDriver = DriverEntity.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        DriverEntity savedDriver = driverService.createNewDriver(newDriver);
        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);
        return modelMapper.map(savedDriver, DriverDto.class);
    }
}

package com.vatsaladhiya.routeq.routeq.controllers;

import com.vatsaladhiya.routeq.routeq.dtos.DriverRideDto;
import com.vatsaladhiya.routeq.routeq.dtos.RideStartDto;
import com.vatsaladhiya.routeq.routeq.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping(path = "/acceptRide/{rideRequestId}")
    public ResponseEntity<DriverRideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping(path = "/startRide/{rideId}")
    public ResponseEntity<DriverRideDto> startRide(@PathVariable Long rideId, @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(rideId, rideStartDto.getOtp()));
    }

    @PostMapping(path = "/cancelRide/{rideId}")
    public ResponseEntity<DriverRideDto> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @PostMapping(path = "/endRide/{rideId}")
    public ResponseEntity<DriverRideDto> endRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.endRide(rideId));
    }
}

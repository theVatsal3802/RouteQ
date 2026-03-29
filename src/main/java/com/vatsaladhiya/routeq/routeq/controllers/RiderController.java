package com.vatsaladhiya.routeq.routeq.controllers;

import com.vatsaladhiya.routeq.routeq.dtos.RideRequestDto;
import com.vatsaladhiya.routeq.routeq.dtos.RiderRideDto;
import com.vatsaladhiya.routeq.routeq.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rider")
@RequiredArgsConstructor
public class RiderController {
    private final RiderService riderService;

    @PostMapping(path = "/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
        return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
    }

    @PostMapping(path = "/cancelRide/{rideId}")
    public ResponseEntity<RiderRideDto> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }
}

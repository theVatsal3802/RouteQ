package com.vatsaladhiya.vatsal.routeq.routeq.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RiderDTO {
    private UserDTO user;
    private Double rating;
}

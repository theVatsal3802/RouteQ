package com.vatsaladhiya.vatsal.routeq.routeq.dtos;

import com.vatsaladhiya.vatsal.routeq.routeq.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String name;
    private String email;
    private Set<Role> roles;
}

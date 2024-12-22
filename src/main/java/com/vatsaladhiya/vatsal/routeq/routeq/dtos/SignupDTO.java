package com.vatsaladhiya.vatsal.routeq.routeq.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {
    private String name;
    private String email;
    private String password;

}

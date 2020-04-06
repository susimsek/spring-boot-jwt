package com.jwt.springbootjwt.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;
}

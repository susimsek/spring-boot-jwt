package com.jwt.springbootjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//gelen reuestdeki username ve passowrdü mapledik
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {

    private String username;
    private String password;
}

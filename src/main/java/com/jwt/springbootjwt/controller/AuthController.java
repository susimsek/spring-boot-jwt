package com.jwt.springbootjwt.controller;

import com.jwt.springbootjwt.dto.Credentials;
import com.jwt.springbootjwt.jwt.TokenManager;
import com.jwt.springbootjwt.shared.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    //AuthenticationManager i çağırdık
    private final AuthenticationManager authenticationManager;

    private final TokenManager tokenManager;

    @PostMapping("/api/v1/token")
    public ResponseEntity<?> login(@RequestBody Credentials loginRequest){
        try {
            //auth işlemi yapıldı
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

            String token = tokenManager.generateToken(loginRequest.getUsername());
            JwtResponse jwtResponse = new JwtResponse(token);//token oluşturup döndük

            return ResponseEntity.ok(jwtResponse);

        }catch (Exception e){
            throw e;
        }
    }
}

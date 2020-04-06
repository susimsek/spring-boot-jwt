package com.jwt.springbootjwt.controller;

import com.jwt.springbootjwt.model.User;
import com.jwt.springbootjwt.service.UserService;
import com.jwt.springbootjwt.shared.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    //user servisi çağırdık
    private final UserService userService;

    //@Valid anatasyonu ile datayı alırken validasyon yapıyoruz
    @PostMapping("/api/v1/users")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user){
        userService.createUser(user);//user oluştu
        GenericResponse response=new GenericResponse("user created");//bodyde bu mesaj dönüldü
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

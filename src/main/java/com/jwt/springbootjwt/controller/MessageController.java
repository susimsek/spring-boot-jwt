package com.jwt.springbootjwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//rest kontroller tanımladık
@RequestMapping("/api/v1/message")///message pathinden yayınlanacak
public class MessageController {



    //get methodu olduğunu belirtitk
    @GetMapping
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("Merhaba JWT");//bodyde bu messajı döner.200 status ile

    }
}

package com.jwt.springbootjwt.service;

import com.jwt.springbootjwt.model.User;
import com.jwt.springbootjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));//password encode edildi
        userRepository.save(user);
    }

}

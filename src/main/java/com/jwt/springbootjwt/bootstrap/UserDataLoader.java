package com.jwt.springbootjwt.bootstrap;

import com.jwt.springbootjwt.model.User;
import com.jwt.springbootjwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDataLoader implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("test1","pass1","test158@gmail.com");
        User user2 = new User("test2","pass2","test258@gmail.com");

        //userlar oluşturuldu
        userService.createUser(user1);
        userService.createUser(user2);
    }
}

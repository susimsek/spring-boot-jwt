package com.jwt.springbootjwt.jwt;

import com.jwt.springbootjwt.model.User;
import com.jwt.springbootjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



//UserDetails Servisden implemente ettik
//Springin Auth Manageri default bı sınıfı çağıracaktır
@Service //bu anatasyon ile inject ettik
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    //UserServiceyi çağırdık
    private final UserRepository userRepository;


    //User Detailsin implementasyonlarından biri userdir
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        //user databasede yok ise
        if(user == null){
            throw new UsernameNotFoundException(username);//username yok ise not found exception fırlattık.
        }
        return user;
    }
}

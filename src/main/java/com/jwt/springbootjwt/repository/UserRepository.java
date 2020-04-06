package com.jwt.springbootjwt.repository;


import com.jwt.springbootjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//repository katmanı olşuturuldu.
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUsername(String username);
}

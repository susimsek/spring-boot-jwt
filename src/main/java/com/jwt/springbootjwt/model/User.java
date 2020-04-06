package com.jwt.springbootjwt.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @NotNull
    @Size(min = 4,max = 256)
    private String username;

    @NonNull
    @NotNull
    @Size(min = 4,max = 256)
    private String password;

    @NonNull
    @NotNull
    @Size(min = 4,max = 256)
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

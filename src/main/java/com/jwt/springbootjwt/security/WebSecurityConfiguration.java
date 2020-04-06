package com.jwt.springbootjwt.security;

import com.jwt.springbootjwt.jwt.JwtAuthenticationEntryPoint;
import com.jwt.springbootjwt.jwt.JwtTokenFilter;
import com.jwt.springbootjwt.jwt.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity//web security enable ettik
@Configuration//bean tanımlamak için gerekli
@EnableGlobalMethodSecurity(prePostEnabled = true)//tüm methotları security yaptık
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //JwtTokenFilterı çağırdık
    private final JwtTokenFilter jwtTokenFilter;

    //Jwt authentication entrypointi çağırdık
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtUserDetailsService jwtUserDetailsService;

    private final PasswordEncoder passwordEncoder;


    //AuthenticationManagerden bean olşuturduk
    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//csrfi disable ettik
        http.authorizeRequests().antMatchers("/h2-console/**","/api/v1/token","/api/v1/users").permitAll().//bu requestlere izin verdik
                anyRequest().authenticated();//login ile başlayan istekleri permit ettik ve h2 consoleye açtık

        http.headers().frameOptions().disable();//frame optionu h2 console için disable ettik

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//sessionu stateless yaptık
        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);//spring defaultda 403 döner,401 dönmesi için ayarladık.custom entrypointi çağırdık

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);//filtremizi verdik.
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);//password encoder atadık
    }

}

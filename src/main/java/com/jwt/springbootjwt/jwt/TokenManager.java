package com.jwt.springbootjwt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

//Token işlemleri için kullanacağız
//Tüm componentler içerisinde erişibileceğimiz bir servis oldu
@Service
public class TokenManager {

    // jwt token secreti aldık
    @Value("${jwt.token.secret}")
    private String secret;

    // token kaç saniye sonra deaktif olacağını belirler
    @Value("${jwt.token.exp}")
    private int exp;

    private Key getSigningKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        System.out.println(Keys.hmacShaKeyFor(keyBytes).getAlgorithm());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //aldığı usernameye göre token üretecek
   public String generateToken(String username){

       return Jwts.builder().
               setSubject(username). //paramatre gelen usernameyi subject olarak atadık
               setExpiration(new Date(System.currentTimeMillis() + exp*1000)). //token ne zamana kadar geçerli
               setIssuer("http://localhost:8080/login"). //kim bunu imzaladı,kim yarattı
               setIssuedAt(new Date(System.currentTimeMillis())).//hangi tarihte token yaratıldı
               signWith(getSigningKey()).
               compact();
    }

    //tokenin validate olup olmadığını gösterecek boolean olarak
    public boolean tokenValidate(String token){
       //username null değilse ve token expire değilse
        if(getUsernameFromToken(token) != null && isExpired(token)){
            return true;
        }
        return false;
    }

    //token alıp geriye username döndürecek
    public String getUsernameFromToken(String token){
        Claims claims = getClaims(token);
        return claims.getSubject();//subject değerinden usernameyi aldık
    }

    //token expire date geçmiş mi
    public boolean isExpired(String token){
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));//şuanki zamanın gerisindeyse expiration bizim için yeterli
    }

    //token validatede dönen claim nesnesi
    private Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

}

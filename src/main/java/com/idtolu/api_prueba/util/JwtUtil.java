package com.idtolu.api_prueba.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    public static final String KEYWORD = "35CU314";

    public static String generarToken(String usuario, String rol) {

        String token = Jwts.builder()
                .setId("jwt" + usuario)
                .claim("usuario", usuario)                
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1))) 
                .signWith(SignatureAlgorithm.HS512, KEYWORD.getBytes())
                .compact();

        return token;
    }
}


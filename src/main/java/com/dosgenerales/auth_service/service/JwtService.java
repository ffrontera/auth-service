package com.dosgenerales.auth_service.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dosgenerales.auth_service.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    public String generateToken(Usuario usuario) {
        return JWT.create()
                .withSubject(usuario.getEmail())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() +1000 * 60 * 60 * 10))
                .withClaim("role", usuario.getRole().name())
                .sign(Algorithm.HMAC256(secretKey));
    }

    public DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(secretKey))
                .build()
                .verify(token);
    }

    public String getEmailFromToken(String token) {
        return verifyToken(token).getSubject();
    }
}

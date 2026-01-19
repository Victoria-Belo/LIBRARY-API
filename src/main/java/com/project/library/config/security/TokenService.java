package com.project.library.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.project.library.model.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author Victoria
 */
@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm signature = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("hermes")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpirationdate())
                    .sign(signature);

        } catch (JWTCreationException e) {
            throw new RuntimeException("JWT Creation Exception :", e);
        }
    }

    //token é válido?
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("hermes")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            throw new RuntimeException("JWT Verification Exception :",e);
        }
    }

    public Instant getExpirationdate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));    }
}

package saboroso.saborosoburguer.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.entities.UserEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class Token {

    @Value ("${api.security.token.secret}")
    private String secret;
    private final String issuer = "saboroso-burguer";

    public String generateToken(UserEntity user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        }
        catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token!", e);
        }
    }
    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException e) {
            return "";
        }
    }
    public Instant getExpirationDate() {
        return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"));
    }
}

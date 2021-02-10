package com.wani.jobstudy.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JWTUtil {

    public static final String AUTH_HEADER = "Authentication";
    public static final String REFRESH_HEADER = "refresh-token";
    public static final String BEARER = "Bearer ";

    private Algorithm AL;

    public enum TokenType {
        access,
        refresh
    }

    public JWTUtil() {
        this.AL = Algorithm.HMAC512(JwtProperties.secret);
    }

    public String generate(String username) {
        return generate(username, TokenType.access);
    }

    public String generate(String username, TokenType type) {
        return JWT.create().withSubject(username)
                .withClaim("exp", Instant.now().getEpochSecond() + getLifeTime(type))
                .sign(AL);
    }

    private long getLifeTime(TokenType type) {
        if (type == TokenType.access) {
            return JwtProperties.tokenRefreshTime;
        }

        return JwtProperties.tokenLifeTime;
    }

    public VerifyResult verify(String token) {
        try {
            DecodedJWT decoded = JWT.require(AL).build().verify(token);
            return VerifyResult.builder().username(decoded.getSubject()).result(true).build();
        } catch (JWTVerificationException ex) {
            DecodedJWT decode = JWT.decode(token);
            return VerifyResult.builder().username(decode.getSubject()).result(false).build();
        }
    }

}

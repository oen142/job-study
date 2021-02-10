package com.wani.jobstudy.config.jwt;

public class JwtRefreshTokenException extends RuntimeException {

    public JwtRefreshTokenException(String message) {
        super("리프레쉬 토큰이 필요합니다. : " + message);
    }
}

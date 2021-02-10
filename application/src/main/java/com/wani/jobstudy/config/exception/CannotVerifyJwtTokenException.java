package com.wani.jobstudy.config.exception;

import com.wani.jobstudy.config.jwt.UserLogin;

public class CannotVerifyJwtTokenException extends IllegalArgumentException {
    public CannotVerifyJwtTokenException(UserLogin userLogin) {
        super("알 수 없는 타입 : " + userLogin.getType());
    }
}

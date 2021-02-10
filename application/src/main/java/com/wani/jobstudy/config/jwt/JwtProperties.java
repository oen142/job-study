package com.wani.jobstudy.config.jwt;

import lombok.Getter;

@Getter
public class JwtProperties {

    public final static String secret = "default-secret-value";
    public final static long tokenLifeTime = 60000;
    public final static long tokenRefreshTime = 24 * 60 * 60;

}

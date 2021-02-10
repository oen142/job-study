package com.wani.jobstudy.integration;


public class Tokens {

    private String accessToken;
    private String refreshToken;


    public Tokens() {
    }

    public Tokens(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}

package com.wani.jobstudy.member.dto;

public class MemberAuthResponse {
    private boolean auth;


    public MemberAuthResponse(boolean auth) {
        this.auth = auth;
    }

    public boolean isAuth() {
        return auth;
    }
}

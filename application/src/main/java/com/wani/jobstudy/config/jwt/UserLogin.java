package com.wani.jobstudy.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserLogin {

    public enum Type {
        login,
        refresh
    }

    private Type type;
    private String username;
    private String password;
    private String refreshToken;

}

package com.wani.jobstudy.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginMember {
    private Long id;
    private String email;
    private String username;

}

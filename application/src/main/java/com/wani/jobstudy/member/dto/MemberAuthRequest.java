package com.wani.jobstudy.member.dto;

import lombok.Getter;

@Getter

public class MemberAuthRequest {

    private Long memberId;
    private String email;
    private String emailAuth;

}

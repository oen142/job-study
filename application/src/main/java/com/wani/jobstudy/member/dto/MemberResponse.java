package com.wani.jobstudy.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberResponse {
    private final Long id;
    private final String email;
    private final String username;

}

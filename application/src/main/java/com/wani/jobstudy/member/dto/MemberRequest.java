package com.wani.jobstudy.member.dto;

import com.wani.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class MemberRequest {

    private String email;
    private String password;
    private String username;

    public Member toMember() {
        return new Member(email, password, username);
    }
}

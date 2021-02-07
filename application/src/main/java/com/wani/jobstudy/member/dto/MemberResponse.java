package com.wani.jobstudy.member.dto;

import com.wani.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberResponse {
    private final Long id;
    private final String email;
    private final String username;

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getEmail(), member.getUsername());
    }
}

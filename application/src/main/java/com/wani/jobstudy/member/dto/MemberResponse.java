package com.wani.jobstudy.member.dto;

import com.wani.domain.member.domain.Member;
import com.wani.domain.member.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class MemberResponse {
    private final Long id;
    private final String email;
    private final String username;
    private final Set<UserRole> userRoleList;

    public MemberResponse(Long id, String email, String username, Set<UserRole> userRoleList) {
        this.userRoleList = new HashSet<>();
        this.id = id;
        this.email = email;
        this.username = username;
        this.userRoleList.addAll(userRoleList);
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getEmail(), member.getUsername(), member.getUserRoles());
    }
}

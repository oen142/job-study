package com.wani.jobstudy.member.dto;

import com.wani.domain.member.domain.Member;
import com.wani.domain.member.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class MemberRequest {

    private String email;
    private String username;
    private String password;

    private PasswordEncoder passwordEncoder;

    public Member toMember(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        return Member.ofMember(email, username, passwordEncoder.encode(password));
    }

    public Member toAdminMember(PasswordEncoder passwordEncoder) {
        return Member.ofAdmin(email, username, passwordEncoder.encode(password));
    }
}

package com.wani.jobstudy.integration;

import com.wani.domain.member.domain.Member;
import com.wani.jobstudy.member.application.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JwtRefreshableTwoUserIntegrationTest {

    @Autowired
    protected MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    protected Member member1;
    protected Member member2;


}

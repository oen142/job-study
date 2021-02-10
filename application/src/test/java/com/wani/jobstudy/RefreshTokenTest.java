package com.wani.jobstudy;

import com.wani.jobstudy.config.jwt.JWTUtil;
import com.wani.jobstudy.member.application.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RefreshTokenTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JWTUtil jwtUtil;

    @BeforeEach
    void init() {
        prepareTwoUsers();


    }

    private void prepareTwoUsers() {

    }


}

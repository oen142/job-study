package com.wani.jobstudy.auth.application;

import com.wani.domain.member.domain.Member;
import com.wani.domain.member.repository.MemberRepository;
import com.wani.jobstudy.auth.dto.TokenRequest;
import com.wani.jobstudy.auth.dto.TokenResponse;
import com.wani.jobstudy.auth.infrastructure.JwtTokenProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";

    private AuthService authService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp(){
        authService = new AuthService(memberRepository , jwtTokenProvider);
    }

    @Test
    @DisplayName("로그인 테스트")
    void login() {
        when(memberRepository.findByEmail(anyString())).thenReturn(Optional.of(new Member(EMAIL , USERNAME, PASSWORD  )));
        when(jwtTokenProvider.createToken(anyString())).thenReturn("TOKEN");

        TokenResponse tokenResponse = authService.login(new TokenRequest(EMAIL, PASSWORD));

        System.out.println("tokenResponse = " + tokenResponse.getAccessToken());
        Assertions.assertThat(tokenResponse.getAccessToken()).isNotBlank();
    }
}

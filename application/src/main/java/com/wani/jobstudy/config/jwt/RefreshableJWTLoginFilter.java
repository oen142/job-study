package com.wani.jobstudy.config.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wani.domain.member.domain.Member;
import com.wani.jobstudy.config.exception.CannotVerifyJwtTokenException;
import com.wani.jobstudy.member.application.MemberService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RefreshableJWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final MemberService memberService;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public RefreshableJWTLoginFilter(AuthenticationManager authenticationManager, MemberService memberService, JWTUtil jwtUtil, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLogin userLogin = objectMapper.readValue(request.getInputStream(), UserLogin.class);

        if (userLogin.getType().equals(UserLogin.Type.login)) {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword(), null));
        } else if (userLogin.getType().equals(UserLogin.Type.refresh)) {
            if (StringUtils.isEmpty(userLogin.getRefreshToken())) {
                throw new JwtRefreshTokenException(userLogin.getRefreshToken());
            }

            VerifyResult result = jwtUtil.verify(userLogin.getRefreshToken());
            if (result.isResult()) {
                Member member = memberService.findMemberAuth(result.getUsername());
                return new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
            }
            throw new TokenExpiredException("리프레시 토큰 만료");

        }else{
            throw new CannotVerifyJwtTokenException(userLogin);
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        Member member = (Member) authResult.getPrincipal();
        response.addHeader(JWTUtil.AUTH_HEADER, JWTUtil.BEARER + jwtUtil.generate(member.getUsername(), JWTUtil.TokenType.access));
        response.addHeader(JWTUtil.REFRESH_HEADER, jwtUtil.generate(member.getUsername(), JWTUtil.TokenType.refresh));

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("unsuccessfulAuthentication : " + failed.getMessage());
        super.unsuccessfulAuthentication(request, response, failed);

    }
}

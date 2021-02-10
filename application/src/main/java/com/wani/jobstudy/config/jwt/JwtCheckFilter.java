package com.wani.jobstudy.config.jwt;

import com.wani.domain.member.domain.Member;
import com.wani.jobstudy.member.application.MemberService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtCheckFilter extends BasicAuthenticationFilter {
    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    public JwtCheckFilter(AuthenticationManager authenticationManager, MemberService memberService, JWTUtil jwtUtil) {
        super(authenticationManager);
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain
    ) throws IOException, ServletException {
        String token = request.getHeader(JWTUtil.AUTH_HEADER);
        System.out.println("token : " + token);
        if (token == null || !token.startsWith(JWTUtil.BEARER)) {
            System.out.println("여기서 걸립니다.");
            chain.doFilter(request, response);
            return;
        }
        VerifyResult result = jwtUtil.verify(token.substring(JWTUtil.BEARER.length()));
        System.out.println("여기는 어딜까요?");
        if (result.isResult()) {
            Member member = memberService.findMemberAuth(result.getUsername());
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities())
            );
            System.out.println("role : " + member.getUsername());
            System.out.println("role : " + member.getPassword());
            System.out.println("role : " + member.getUserRoles().toString());
            System.out.println("role : " + member.getUserRoles().toString());
            System.out.println("authority : " + member.getAuthorities().toString());
            System.out.println("authority : " + member.getAuthorities().size());
            System.out.println("로그인이 되었습니다.");
        }
        chain.doFilter(request, response);
    }
}

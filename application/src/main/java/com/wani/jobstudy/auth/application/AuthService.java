package com.wani.jobstudy.auth.application;

import com.wani.domain.member.domain.Member;
import com.wani.domain.member.repository.MemberRepository;
import com.wani.jobstudy.auth.domain.LoginMember;
import com.wani.jobstudy.auth.dto.TokenRequest;
import com.wani.jobstudy.auth.dto.TokenResponse;
import com.wani.jobstudy.auth.infrastructure.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private MemberRepository memberRepository;
    private JwtTokenProvider jwtTokenProvider;


    public LoginMember findMemberByToken(String credentials) {
        if (!jwtTokenProvider.validateToken(credentials)) {
            throw new AuthorizationException();
        }
        String email = jwtTokenProvider.getPayload(credentials);
        Member member = memberRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        return new LoginMember(member.getId(), member.getEmail(), member.getUsername());
    }

    public TokenResponse login(TokenRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(AuthorizationException::new);


        member.checkPassword(request.getPassword());

        String token = jwtTokenProvider.createToken(request.getEmail());

        return new TokenResponse(token);
    }
}

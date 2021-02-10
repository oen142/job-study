package com.wani.jobstudy.member.application;

import com.wani.domain.member.domain.Member;
import com.wani.domain.member.repository.MemberRepository;
import com.wani.jobstudy.member.dto.MemberRequest;
import com.wani.jobstudy.member.dto.MemberResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MemberResponse createAdmin(MemberRequest request) {
        Member admin = memberRepository.save(request.toAdminMember(passwordEncoder));
        return MemberResponse.of(admin);
    }

    @Transactional
    public MemberResponse createMember(MemberRequest request) {
        Member member = memberRepository.save(request.toMember(passwordEncoder));
        return MemberResponse.of(member);
    }

    @Transactional(readOnly = true)
    public Member findMember(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        return Member.of(member);
    }
    @Transactional(readOnly = true)
    public Member findMemberAuth(String username) {
        return memberRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }
    @Transactional
    public MemberResponse findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(RuntimeException::new);
        return MemberResponse.of(member);
    }

    @Transactional
    public void updateMember(Long id, MemberRequest param) {
        Member member = memberRepository.findById(id).orElseThrow(RuntimeException::new);
        member.update(param.toMember(passwordEncoder));
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }
}

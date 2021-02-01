package com.wani.jobstudy.service;

import com.wani.domain.member.domain.Member;
import com.wani.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {


    private final MemberRepository memberRepository;


    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public void save() {
        List<Member> members = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            members.add(Member.builder()
                    .username(i + "test")
                    .password(i + "pass").build());
        }
        memberRepository.saveAll(members);
    }
}

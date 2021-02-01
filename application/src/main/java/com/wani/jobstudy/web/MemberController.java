package com.wani.jobstudy.web;

import com.wani.domain.member.domain.Member;
import com.wani.jobstudy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member")
    public String insertMember() {
        memberService.save();
        return "ok";
    }

    @GetMapping("/member")
    public List<Member> getMembers() {
        return memberService.getMembers();
    }
}

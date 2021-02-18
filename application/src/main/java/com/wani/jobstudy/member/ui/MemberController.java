package com.wani.jobstudy.member.ui;


import com.wani.jobstudy.member.application.MemberService;
import com.wani.jobstudy.member.dto.MemberAuthRequest;
import com.wani.jobstudy.member.dto.MemberAuthResponse;
import com.wani.jobstudy.member.dto.MemberRequest;
import com.wani.jobstudy.member.dto.MemberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity createMember(@RequestBody MemberRequest request) {
        MemberResponse response = memberService.createMember(request);
        return ResponseEntity.created(URI.create("/members/" + response.getId())).build();
    }

    @PostMapping("/members/email/auth")
    public ResponseEntity validAuthMember(@RequestBody MemberAuthRequest request) {
        MemberAuthResponse response = memberService.vaildateMemberEmailAuth(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable Long id) {
        MemberResponse member = memberService.findMember(id);

        return ResponseEntity.ok().body(member);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long id, @RequestBody MemberRequest param) {
        memberService.updateMember(id, param);
        return ResponseEntity.ok().build();
    }


}

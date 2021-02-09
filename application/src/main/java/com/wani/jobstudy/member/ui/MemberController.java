package com.wani.jobstudy.member.ui;

import com.wani.jobstudy.auth.domain.AuthenticationPrincipal;
import com.wani.jobstudy.auth.domain.LoginMember;
import com.wani.jobstudy.member.application.MemberService;
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
    public ResponseEntity createMembers(@RequestBody MemberRequest request) {
        MemberResponse response = memberService.createMember(request);

        return ResponseEntity.created(URI.create("/members" + response.getId())).build();
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

    @GetMapping("/members/me")
    public ResponseEntity<MemberResponse> findMemberOfMine(@AuthenticationPrincipal LoginMember loginMember) {
        MemberResponse member = memberService.findMember(loginMember.getId());
        return ResponseEntity.ok().body(member);
    }

    @PutMapping("/members/me")
    public ResponseEntity<MemberResponse> updateMemberOfMine(@AuthenticationPrincipal LoginMember loginMember, @RequestBody MemberRequest param){
        memberService.updateMember(loginMember.getId() , param);
        return ResponseEntity.ok().build();
    }


}

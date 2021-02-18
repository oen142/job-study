package com.wani.domain.member.repository;

import com.wani.domain.member.domain.MemberEmail;

import java.util.Optional;

public interface MemberEmailRepositoryCustom {

    MemberEmail findMemberIdAndEmail(Long memberId, String memberEmail);
}

package com.wani.domain.member.repository;

import com.wani.domain.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {

    public List<Member> findByEmailDescUserName();

    public List<Member> findByEmailAscUserName();

    Optional<Member> findByUsername(String username);

    Optional<Member> findByIdAndEmail(Long id, String email);
}

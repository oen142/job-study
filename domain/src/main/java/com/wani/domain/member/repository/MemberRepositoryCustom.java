package com.wani.domain.member.repository;

import com.wani.domain.member.domain.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    public List<Member> findByEmailDescUserName();
    public List<Member> findByEmailAscUserName();
}

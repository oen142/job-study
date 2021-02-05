package com.wani.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wani.domain.member.domain.Member;
import com.wani.domain.member.domain.QMember;

import javax.persistence.EntityManager;
import java.util.List;

import static com.wani.domain.member.domain.QMember.*;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Member> findByEmailDescUserName() {
        return queryFactory.select(member)
                .from(member)
                .orderBy(member.username.desc())
                .fetch();
    }

    @Override
    public List<Member> findByEmailAscUserName() {
        return queryFactory.select(member)
                .from(member)
                .orderBy(member.username.asc())
                .fetch();
    }
}

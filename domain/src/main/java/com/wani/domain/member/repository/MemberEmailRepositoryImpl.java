package com.wani.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wani.domain.member.domain.MemberEmail;
import com.wani.domain.member.domain.QMember;
import com.wani.domain.member.domain.QMemberEmail;


import javax.persistence.EntityManager;

public class MemberEmailRepositoryImpl implements MemberEmailRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberEmailRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public MemberEmail findMemberIdAndEmail(Long memberId, String memberEmail) {
        return queryFactory.selectFrom(QMemberEmail.memberEmail)
                .join(QMemberEmail.memberEmail.member, QMember.member)
                .fetchJoin()
                .where(QMemberEmail.memberEmail.email.eq(memberEmail))
                .where(QMemberEmail.memberEmail.member.id.eq(memberId))
                .fetchOne();
    }
}

package com.wani.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wani.domain.member.domain.Member;
import com.wani.domain.member.domain.QMember;
import com.wani.domain.member.domain.QMemberEmail;
import com.wani.domain.member.domain.QUserRole;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.wani.domain.member.domain.QMember.*;
import static com.wani.domain.member.domain.QMemberEmail.memberEmail;
import static com.wani.domain.member.domain.QUserRole.*;

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

    @Override
    public Optional<Member> findByUsername(String username) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .join(member.userRoles, userRole)
                .fetchJoin()
                .where(member.username.eq(username))
                .fetchOne());
    }

    @Override
    public Optional<Member> findByIdAndEmail(Long id, String memberEmail) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .join(member.email, QMemberEmail.memberEmail)
                .fetchJoin()
                .where(QMemberEmail.memberEmail.email.eq(memberEmail))
                .fetchOne());
    }
}

package com.wani.domain.member.repository;

import com.wani.domain.member.domain.MemberEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberEmailRepository extends JpaRepository<MemberEmail, Long>, MemberEmailRepositoryCustom, QuerydslPredicateExecutor<MemberEmail> {
}

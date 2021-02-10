package com.wani.domain.member.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    protected UserRole() {
    }

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    public UserRole(MemberRole role) {
        this.role = role;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String getAuthority() {
        return role.toString();
    }
}

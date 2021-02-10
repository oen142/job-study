package com.wani.domain.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wani.domain.common.domain.CommonEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "email", "username"})
public class Member extends CommonEntity implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String email;

    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    public Member(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Member(String email, String username, String password, Set<UserRole> userRoles) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
    }


    public static Member of(Member member) {
        return new Member(member.email, member.username, member.password);
    }

    public static Member ofAdmin(Member member) {
        Set<UserRole> adminRoles = new HashSet<>();
        UserRole adminRole = new UserRole(MemberRole.ROLE_ADMIN);
        UserRole userRole = new UserRole(MemberRole.ROLE_USER);
        adminRoles.add(adminRole);
        adminRoles.add(userRole);
        Member returnMember = new Member(member.email, member.username, member.password, adminRoles);
        adminRole.setMember(returnMember);
        userRole.setMember(returnMember);

        return returnMember;
    }

    public static Member ofMember(Member member) {
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(MemberRole.ROLE_USER);
        userRoles.add(userRole);
        Member returnMember = new Member(member.email, member.username, member.password, userRoles);
        userRole.setMember(returnMember);

        return returnMember;
    }

    public void checkPassword(String password) {
        if (!StringUtils.equals(this.password, password)) {
            throw new AuthorizationException();
        }
    }

    public void update(Member member) {
        this.email = member.email;
        this.password = member.password;
        this.username = member.username;
    }

    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRoles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

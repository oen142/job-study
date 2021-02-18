package com.wani.domain.member.domain;

import com.wani.domain.common.domain.CommonEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MemberEmail email;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Keyword> keywords = new HashSet<>();


    private Member(String email, String username, String password) {
        this.email = MemberEmail.ofNew(email);
        this.username = username;
        this.password = password;
    }

    private Member(String email, String username, String password, Set<UserRole> userRoles) {
        this.email = MemberEmail.ofNew(email);
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
    }


    public static Member ofAdmin(String email, String username, String password) {
        Set<UserRole> adminRoles = new HashSet<>();
        UserRole adminRole = new UserRole(MemberRole.ROLE_ADMIN);
        UserRole userRole = new UserRole(MemberRole.ROLE_USER);
        adminRoles.add(adminRole);
        adminRoles.add(userRole);
        Member returnMember = new Member(email, username, password, adminRoles);
        adminRole.setMember(returnMember);
        userRole.setMember(returnMember);

        return returnMember;
    }

    public static Member ofMember(String email, String username, String password) {
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(MemberRole.ROLE_USER);
        userRoles.add(userRole);
        Member returnMember = new Member(email, username, password, userRoles);

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
        return email.isLocked();
    }

    @Override
    public boolean isAccountNonLocked() {
        return email.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return email.isAuthenticated();
    }

    @Override
    public boolean isEnabled() {
        return email.isEnabled();
    }

    public boolean validMemberEmailAuth(String authToken){
        return email.validAuth(authToken);
    }

}

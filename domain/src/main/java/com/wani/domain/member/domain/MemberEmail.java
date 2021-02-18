package com.wani.domain.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long id;

    @OneToOne(mappedBy = "email", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Member member;

    private String email;

    @Embedded
    private EmailAuth emailAuth;

    private boolean isLocked;
    private boolean isAuthenticated;
    private boolean isExpired;

    public MemberEmail(String email, EmailAuth emailAuth, boolean isLocked, boolean isAuthenticated, boolean isExpired) {
        this.email = email;
        this.emailAuth = emailAuth;
        this.isLocked = isLocked;
        this.isAuthenticated = isAuthenticated;
        this.isExpired = isExpired;
    }

    public static MemberEmail ofNew(String email) {
        return new MemberEmail(email, EmailAuth.ofNew(), false, false, false);
    }

    public String getEmail() {
        return email;
    }

    public boolean validAuth(String authNumber) {

        if (emailAuth.isValid(authNumber)) {
            isLocked = true;
            isAuthenticated = true;
            isExpired = true;
            emailAuth = EmailAuth.ofNew();
            System.out.println("\"됨??\" = " + "됨??");
            return true;
        }

        throw new RuntimeException("계정 권한 변경을 못했습니다.");
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public boolean isEnabled() {
        return isExpired;
    }
}

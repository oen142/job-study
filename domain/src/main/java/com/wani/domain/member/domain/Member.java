package com.wani.domain.member.domain;

import com.wani.domain.common.domain.CommonEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(of = {"id", "email", "username"})
public class Member extends CommonEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    public Member(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
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

}

package com.wani.domain.member.domain;

import com.wani.domain.common.domain.CommonEntity;
import lombok.*;

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

}

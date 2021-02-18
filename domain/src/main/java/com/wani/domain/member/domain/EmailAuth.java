package com.wani.domain.member.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailAuth {

    private String authString;
    private LocalDateTime generatedDateTime;
    private LocalDateTime expiredDateTime;
    private LocalDateTime validDateTime;


    private EmailAuth(String authString, LocalDateTime generatedDateTime, LocalDateTime expiredDateTime, LocalDateTime validDateTime) {
        this.authString = authString;
        this.generatedDateTime = generatedDateTime;
        this.expiredDateTime = expiredDateTime;
        this.validDateTime = validDateTime;
    }

    public static EmailAuth ofNew() {
        return new EmailAuth(generateRandomString(), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
    }

    public static String generateRandomString() {
        return "test";
    }

    public boolean isValid(String authNumber) {
        if (!StringUtils.equals(authNumber, this.authString)) {
            throw new RuntimeException("auth Token이 맞지 않습니다.");
        }

        return true;
    }
}

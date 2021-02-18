package com.wani.domain.member.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long id;


    private String keywordName;


    private Keyword(String keywordName) {
        this.keywordName = keywordName;

    }

    public static Keyword ofNew(String keywordName) {
        validKeywordName(keywordName);
        return new Keyword(keywordName);
    }

    private static void validKeywordName(String keywordName) {
        if (keywordName.isBlank() || keywordName.isEmpty()) {
            throw new RuntimeException("키워드 이름이 옳지 않습니다.");
        }
    }

    public void updateKeywordName(String keywordName) {

        validKeywordName(keywordName);
        this.keywordName = keywordName;
    }

    public String getKeywordName() {
        return keywordName;
    }
}

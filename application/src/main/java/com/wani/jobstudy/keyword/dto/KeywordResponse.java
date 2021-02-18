package com.wani.jobstudy.keyword.dto;

import com.wani.domain.member.domain.Keyword;

public class KeywordResponse {
    private String keywordName;

    private KeywordResponse(String keywordName) {
        this.keywordName = keywordName;
    }

    public static KeywordResponse ofResponse(Keyword keyword) {
        return new KeywordResponse(keyword.getKeywordName());
    }

    public String getKeywordName() {
        return keywordName;
    }
}

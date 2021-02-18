package com.wani.jobstudy.keyword.dto;

import com.wani.domain.member.domain.Keyword;

public class KeywordRequest {
    private String keywordName;


    public Keyword toKeyword() {
        return Keyword.ofNew(keywordName);
    }

    public String getKeywordName() {

        return keywordName;
    }
}

package com.wani.domain.board.domain;


import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
public class ContentsBody {

    private String title;
    private String contents;

    protected ContentsBody() {
    }

    public ContentsBody(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}

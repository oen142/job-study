package com.wani.jobstudy.board.dto;

import com.wani.domain.board.domain.Board;
import com.wani.domain.board.domain.ContentsBody;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardRequest {
    private String title;
    private String contents;

    public Board toNewBoard() {
        return Board.ofNew(new ContentsBody(title, contents));
    }
}

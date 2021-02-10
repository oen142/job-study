package com.wani.domain.board.domain;

import com.wani.domain.common.domain.CommonEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Board extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;


    @Embedded
    private ContentsBody contentsBody;

    @Enumerated(value = EnumType.STRING)
    private ReplyFl replyFl;


    protected Board() {
    }

    private Board(ContentsBody contentsBody, ReplyFl replyFl) {
        this.contentsBody = contentsBody;
        this.replyFl = replyFl;
    }

    public static Board ofNew(ContentsBody contentsBody) {
        return new Board(contentsBody, ReplyFl.REPLY_WAIT);
    }

}

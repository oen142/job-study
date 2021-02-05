package com.wani.domain.board.domain;

import com.wani.domain.common.domain.CommonEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;


}

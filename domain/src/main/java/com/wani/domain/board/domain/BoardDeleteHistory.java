package com.wani.domain.board.domain;

import com.wani.domain.common.domain.CommonEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class BoardDeleteHistory extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_delete_history_id")
    private Long id;



}

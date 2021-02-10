package com.wani.jobstudy.board.application;

import com.wani.domain.board.domain.Board;
import com.wani.domain.board.repository.BoardRepository;
import com.wani.jobstudy.board.dto.BoardRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional(readOnly = true)
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    @Transactional
    public Board saveBoard(BoardRequest boardRequest) {
        return boardRepository.save(boardRequest.toNewBoard());
    }


}

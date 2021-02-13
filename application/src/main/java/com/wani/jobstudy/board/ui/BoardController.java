package com.wani.jobstudy.board.ui;

import com.wani.domain.board.domain.Board;
import com.wani.jobstudy.board.application.BoardService;
import com.wani.jobstudy.board.dto.BoardRequest;
import com.wani.jobstudy.board.dto.BoardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boards")
    public ResponseEntity saveBoards(@RequestBody BoardRequest boardRequest) {
        Board board = boardService.saveBoard(boardRequest);
        return ResponseEntity.created(URI.create("/boards/" + board.getId())).build();
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/boards")
    public ResponseEntity<List<Board>> findBoardsAuth() {
        List<Board> boardList = boardService.getBoardList();
        return ResponseEntity.ok().body(boardList);
    }

    @GetMapping("/")
    public ResponseEntity<BoardResponse> updateBoard() {
        return ResponseEntity.ok().body(new BoardResponse());
    }
}

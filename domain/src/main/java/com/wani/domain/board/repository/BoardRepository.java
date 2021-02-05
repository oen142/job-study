package com.wani.domain.board.repository;

import com.wani.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long > {
}

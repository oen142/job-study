package com.wani.domain.board.repository;

import com.wani.domain.board.domain.BoardDeleteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDeleteHistoryRepository extends JpaRepository<BoardDeleteHistory, Long> {
}

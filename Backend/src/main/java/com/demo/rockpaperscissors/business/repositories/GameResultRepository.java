package com.demo.rockpaperscissors.business.repositories;

import com.demo.rockpaperscissors.business.model.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameResultRepository extends JpaRepository<GameResult, Long> {
}

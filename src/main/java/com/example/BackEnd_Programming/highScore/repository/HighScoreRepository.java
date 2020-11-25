package com.example.BackEnd_Programming.highScore.repository;

import com.example.BackEnd_Programming.highScore.HighScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighScoreRepository extends JpaRepository<HighScore, Long> {
}

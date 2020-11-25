package com.example.BackEnd_Programming.score.repository;

import com.example.BackEnd_Programming.score.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}

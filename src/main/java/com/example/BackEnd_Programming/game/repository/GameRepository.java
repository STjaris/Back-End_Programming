package com.example.BackEnd_Programming.game.repository;

import com.example.BackEnd_Programming.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}

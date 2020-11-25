package com.example.BackEnd_Programming.player.repository;

import com.example.BackEnd_Programming.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}

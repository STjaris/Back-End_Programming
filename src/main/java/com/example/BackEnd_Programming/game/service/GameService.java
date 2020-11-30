package com.example.BackEnd_Programming.game.service;

import com.example.BackEnd_Programming.game.Game;
import com.example.BackEnd_Programming.game.GameStatus;
import com.example.BackEnd_Programming.game.repository.GameRepository;
import com.example.BackEnd_Programming.round.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements GameServiceInterface {

    @Autowired
    private GameRepository gameRepository;

    private Game game;

    @Override
    public void getAllGames() {

    }

    public void startNewRound() {
        Round round = new Round();
        Game game = new Game();

        if (game.getGameStatus() == GameStatus.NOTSTARTED) {
        }
    }
}

package nl.hu.vkbep.lingo.game.service;

import nl.hu.vkbep.lingo.game.Game;
import nl.hu.vkbep.lingo.game.GameStatus;
import nl.hu.vkbep.lingo.game.repository.GameRepository;
import nl.hu.vkbep.lingo.round.Round;
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

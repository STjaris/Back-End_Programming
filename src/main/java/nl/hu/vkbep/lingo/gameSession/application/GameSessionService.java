package nl.hu.vkbep.lingo.gameSession.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.gameSession.data.GameSessionRepository;
import nl.hu.vkbep.lingo.gameSession.domain.GameSession;
import nl.hu.vkbep.lingo.player.data.PlayerRepository;
import nl.hu.vkbep.lingo.player.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameSessionService {


    private PlayerRepository playerRepository;
    private GameSessionRepository gameSessionRepository;

    @Autowired
    public GameSessionService(PlayerRepository playerRepository, GameSessionRepository gameSessionRepository) {
        this.playerRepository = playerRepository;
        this.gameSessionRepository = gameSessionRepository;
    }

    public GameSession createNewGameSession(Game game, Long playerid){

        Player player = playerRepository.getPlayerById(playerid);
        List<Game> listOfGames  = new ArrayList<>();
        listOfGames.add(game);

        GameSession gameSession = new GameSession(listOfGames, player, 0);

        gameSessionRepository.save(gameSession);

        return gameSession;

    }

    public void updateGameSession(GameSession gameSession, Game game){

        List<Game> gameList = gameSession.getGames();
        gameList.add(game);
        gameSession.setGames(gameList);

        gameSessionRepository.save(gameSession);
    }

    public GameSession getGameSessionContainingGame(Game game){
        return gameSessionRepository.getGameSessionByGamesIsContaining(game);
    }

}

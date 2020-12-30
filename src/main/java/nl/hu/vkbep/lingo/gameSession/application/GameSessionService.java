package nl.hu.vkbep.lingo.gameSession.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.gameSession.data.GameSessionRepository;
import nl.hu.vkbep.lingo.gameSession.domain.GameSession;
import nl.hu.vkbep.lingo.player.data.PlayerRepository;
import nl.hu.vkbep.lingo.player.domain.Player;
import nl.hu.vkbep.lingo.score.application.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameSessionService {


    private PlayerRepository playerRepository;
    private GameSessionRepository gameSessionRepository;
    private ScoreService scoreService;

    @Autowired
    public GameSessionService(PlayerRepository playerRepository, GameSessionRepository gameSessionRepository, ScoreService scoreService) {
        this.playerRepository = playerRepository;
        this.gameSessionRepository = gameSessionRepository;
        this.scoreService = scoreService;
    }

    public GameSession createNewGameSession(Game game, Long playerid){

        Player player = playerRepository.getPlayerById(playerid);
        List<Game> listOfGames  = new ArrayList<>();
        listOfGames.add(game);

        GameSession gameSession = new GameSession(listOfGames, player, 0);

        gameSessionRepository.save(gameSession);

        return gameSession;

    }

    public GameSession updateGameSession(GameSession gameSession, Game newGame, Game oldGame){

        //UPDATE LIST
        List<Game> gameList = gameSession.getGames();
        gameList.add(newGame);
        gameSession.setGames(gameList);

        //UPDATE SCORE
        double score = gameSession.getTotalScore();
        score += oldGame.getScore();
        gameSession.setTotalScore(score);

        gameSessionRepository.save(gameSession);

        return gameSession;
    }

    public GameSession getGameSessionContainingGame(Game game){
        return gameSessionRepository.getGameSessionByGamesIsContaining(game);
    }

}

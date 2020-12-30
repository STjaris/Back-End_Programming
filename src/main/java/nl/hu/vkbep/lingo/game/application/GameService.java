package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.data.GameRepository;
import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.game.exception.GameFinished;
import nl.hu.vkbep.lingo.game.exception.MaxRoundReached;
import nl.hu.vkbep.lingo.gameSession.application.GameSessionService;
import nl.hu.vkbep.lingo.gameSession.domain.GameSession;
import nl.hu.vkbep.lingo.word.application.WordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class GameService implements GameServiceInterface {

    private List<Long> gameidList = new ArrayList<>();


    private GameRepository gameRepository;
    private WordServiceInterface wordServiceInterface;

    private GameSessionService gameSessionService;

    @Autowired
    public GameService(GameRepository gameRepository, WordServiceInterface wordServiceInterface, GameSessionService gameSessionService) {
        this.gameRepository = gameRepository;
        this.wordServiceInterface = wordServiceInterface;
        this.gameSessionService = gameSessionService;
    }

    public Game createGame(GameType gameType) {
        //CREATE NEW GAME
        Game game = new Game();

        //SET GAMESTATUS
        game.setGameStatus(GameStatus.NOTSTARTED);

        //SET GAMETYPE
        game.setGameType(gameType);

        //SET RANDOM WORD IN GAME
        game.setWord(wordServiceInterface.getRandomWord(game.getGameType()));

        //SAVE GAME
        gameRepository.save(game);

        return game;
    }

    public Map createNewGame(Long playerid) {

        Game game = createGame(GameType.LETTEROF5);

        //CREATE NEW GAMESESSION
        gameSessionService.createNewGameSession(game, playerid);

        //RETURN MAP WITH DATA
        Map map = new HashMap<>();
        map.put("gameid", game.getId());
        map.put("feedback", game.getWord());

        return map;
    }

    @Override
    public Game getById(Long gameid) {
        return gameRepository.getById(gameid);
    }

    @Override
    public Map makeAttempt(Long gameid, String guess) {

        Long wordid = gameRepository.getById(gameid).getWord().getId();
        Map map = new HashMap<>();


        if (roundCountChecker(gameid)) {
            if (wordServiceInterface.attemptChecker(wordid, guess)) {
                Game newGame = checkGameType(gameid);

                map.put("NOTE", "CORRECT");
                map.put("NEWGAME", newGame);
            } else {
                Game game = gameRepository.getById(gameid);
                int roundcount = game.getRoundCount();
                game.setRoundCount(roundcount + 1);
                gameRepository.save(game);

                map.put("FEEDBACK", wordServiceInterface.wordChecker(guess, wordid));
            }
        } else {
            map.put("NOTE", new MaxRoundReached(gameid).getMessage());
        }
        return map;
    }

    public boolean roundCountChecker(Long gameid) {
        return gameRepository.getById(gameid).getRoundCount() < 5;
    }

    public Game checkGameType(Long gameid) {

        Game gamePlayed = gameRepository.getById(gameid);

        if (gamePlayed.getGameType() == GameType.LETTEROF5) {
            //UPDATE SCORE
            score(gamePlayed, 5);

            //NEW GAME
            Game newGame = createGame(GameType.LETTEROF6);
            newGame.setGameType(GameType.LETTEROF6);

            GameSession gameSession = gameSessionService.getGameSessionContainingGame(gamePlayed);
            gameSessionService.updateGameSession(gameSession, newGame, gamePlayed);

            return newGame;
        } else if (gamePlayed.getGameType() == GameType.LETTEROF6) {
            //UPDATE SCORE
            score(gamePlayed, 6);

            //NEW GAME
            Game newGame = createGame(GameType.LETTEROF7);
            newGame.setGameType(GameType.LETTEROF7);

            GameSession gameSession = gameSessionService.getGameSessionContainingGame(gamePlayed);
            gameSessionService.updateGameSession(gameSession, newGame, gamePlayed);

            return newGame;
        } else if (gamePlayed.getGameType() == GameType.LETTEROF7) {
            //UPDATE SCORE
            score(gamePlayed, 7);

            //END GAME
            System.out.println(new GameFinished().getMessage());
            throw new GameFinished();

        } else {
            return gamePlayed;
        }

    }

    public double score(Game game, int multiplier) {

        double score = game.calculation(game.getRoundCount(), multiplier);
        game.setScore(score);

        gameRepository.save(game);

        return score;
    }


}

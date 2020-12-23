package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.data.GameRepository;
import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.game.exception.MaxRoundReached;
import nl.hu.vkbep.lingo.score.application.ScoreService;
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
    private ScoreService scoreService;

    @Autowired
    public GameService(GameRepository gameRepository, WordServiceInterface wordServiceInterface, ScoreService scoreService) {
        this.gameRepository = gameRepository;
        this.wordServiceInterface = wordServiceInterface;
        this.scoreService = scoreService;
    }

    public Game createGame() {
        //CREATE NEW GAME
        Game game = new Game();

        //SET GAMESTATUS
        game.setGameStatus(GameStatus.NOTSTARTED);

        //SET GAMETYPE
        game.setGameType(GameType.LETTEROF5);

        //SET RANDOM WORD IN GAME
        game.setWord(wordServiceInterface.getRandomWord(game.getGameType()));

        //SAVE GAME
        gameRepository.save(game);

        return game;
    }

    public Map createNewGame() {

        //RETURN MAP WITH DATA
        Map map = new HashMap<>();
        map.put("gameid", createGame().getId());
        map.put("feedback", createGame().getWord());

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
                map.put("NOTE", "CORRECT");
                map.put("NEWGAME", checkGameType(gameid));
                checkGameType(gameid);
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
            Game newGame = createGame();
            newGame.setGameType(GameType.LETTEROF6);
            return newGame;
        } else if (gamePlayed.getGameType() == GameType.LETTEROF6) {
            Game newGame = createGame();
            newGame.setGameType(GameType.LETTEROF7);
            return newGame;
        } else {
            return gamePlayed;
        }

    }
}

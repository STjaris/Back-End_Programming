package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.data.GameRepository;
import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.round.application.RoundServiceInterface;
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
    private RoundServiceInterface roundServiceInterface;
    private ScoreService scoreService;

    @Autowired
    public GameService(GameRepository gameRepository, WordServiceInterface wordServiceInterface, RoundServiceInterface roundServiceInterface, ScoreService scoreService) {
        this.gameRepository = gameRepository;
        this.wordServiceInterface = wordServiceInterface;
        this.roundServiceInterface = roundServiceInterface;
        this.scoreService = scoreService;
    }

    public Map createNewGame() {
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

        //RETURN MAP WITH DATA
        Map<String, java.io.Serializable> map = new HashMap<>();
        map.put("gameid", game.getId());
        map.put("feedback", game.getWord().toString());

        return map;
    }

    @Override
    public Map guess(Long gameid, String guess, List<Long> listOfGameid, Long playerid) {

        if (!gameidList.contains(gameid)) {
            gameidList.add(gameid);
        }

        //GET WORDID FROM GAME
        Long wordid = gameRepository.getById(gameid).getWord().getId();

        //SET GAME TO ONGOING AND SAVE
        Game game = gameRepository.getById(gameid);
        game.setGameStatus(GameStatus.ONGOING);
        gameRepository.save(game);

        scoreService.calculateScores(game, gameType(game), playerid, listOfGameid);

        return checkRoundCountPerGame(game, wordid, guess);
    }

    public Map checkRoundCountPerGame(Game game, Long wordid, String guess) {

        //CHECK IF MAX ROUND IS REACHED
        if (game.getRoundCount() < 5) {

            game.setRoundCount(countRoundsPerGame(game.getId()) + 1);
            Map map = new HashMap();

            //ADDS NEW GAME TO MAP
            map.put("Game", checkGameType(roundServiceInterface.playRound(game, wordid, guess), game).toString());

            map.putAll(roundServiceInterface.playRound(game, wordid, guess));

            return map;
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("feedback", "MAX ROUND REACHED");
            map.put("gamestatus", GameStatus.ENDED.toString());

            //CHANGE GAMESTATUS TO ENDED AND SAVE
            game.setGameStatus(GameStatus.ENDED);
            gameRepository.save(game);

            return map;
        }
    }


    public Game checkGameType(Map<String, String> map, Game game) {

        //CREATES NEW GAME IF RETURNS CORRECT ELSE RETURN OLD GAME
        if (map.containsValue("CORRECT") && map.containsKey("note")
                && game.getGameType() == GameType.LETTEROF5) {
            Game newGame = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF6,
                    wordServiceInterface.getRandomWord(GameType.LETTEROF6));
            gameRepository.save(newGame);
            return newGame;

        } else if (map.containsValue("CORRECT") && map.containsKey("note")
                && game.getGameType() == GameType.LETTEROF6) {
            Game newGame = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF7,
                    wordServiceInterface.getRandomWord(GameType.LETTEROF7));
            gameRepository.save(newGame);
            return newGame;
        } else {
            return game;
        }
    }


    public int countRoundsPerGame(Long gameid) {
        Game game = gameRepository.getById(gameid);

        return roundServiceInterface.countRoundPerGame(game);
    }


    @Override
    public Game getById(Long gameid) {
        return gameRepository.getById(gameid);
    }


    public int gameType(Game game) {
        if (game.getGameType() == GameType.LETTEROF5) {
            return 5;
        } else if (game.getGameType() == GameType.LETTEROF6) {
            return 6;
        } else return 7;
    }
}

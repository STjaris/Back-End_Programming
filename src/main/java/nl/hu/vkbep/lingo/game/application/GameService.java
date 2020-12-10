package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.data.GameRepository;
import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.round.application.RoundServiceInterface;
import nl.hu.vkbep.lingo.word.application.WordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class GameService implements GameServiceInterface {


    private GameRepository gameRepository;

    private WordServiceInterface wordServiceInterface;

    private RoundServiceInterface roundServiceInterface;

    @Autowired
    public GameService(GameRepository gameRepository, WordServiceInterface wordServiceInterface, RoundServiceInterface roundServiceInterface) {
        this.gameRepository = gameRepository;
        this.wordServiceInterface = wordServiceInterface;
        this.roundServiceInterface = roundServiceInterface;
    }

    @Override
    public void getAllGames() {

    }

    public Game createNewGame() {

        //CREATE NEW GAME
        Game game = new Game();

        //SET RANDOM WORD IN GAME
        game.setWord(wordServiceInterface.getRandomWord());

        //SET GAMESTATUS
        game.setGameStatus(GameStatus.NOTSTARTED);

        //SET GAMETYPE
        game.setGameType(GameType.LETTEROF5);

        //SAVE GAME
        gameRepository.save(game);

        return game;
    }

    public Game assignGameType(Game game, Long gameid, String guess) {
        if (guess(gameid, guess).containsValue("CORRECT") && game.getGameType() == GameType.LETTEROF5) {
            Game newGame = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF6, wordServiceInterface.getRandomWord());
            gameRepository.save(newGame);

            return newGame;
        } else if (guess(gameid, guess).containsValue("CORRECT") && game.getGameType() == GameType.LETTEROF6) {
            Game newGame = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF7, wordServiceInterface.getRandomWord());
            gameRepository.save(newGame);

            return newGame;
        } else {
            return null;
        }
    }


    @Override
    public Map guess(Long gameid, String guess) {

        //GET WORDID FROM GAME
        Long wordid = gameRepository.getById(gameid).getWord().getId();

        //CREATE NEW GAME
        Game game = gameRepository.getById(gameid);

        //CHECK IF MAX ROUND IS REACHED
        if (game.getRoundCount() < 5) {
            game.setRoundCount(countRoundsPerGame(game.getId()) + 1);

            return roundServiceInterface.playRound(game, wordid, guess);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("feedback", "MAX ROUND REACHED");
            return map;
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
}

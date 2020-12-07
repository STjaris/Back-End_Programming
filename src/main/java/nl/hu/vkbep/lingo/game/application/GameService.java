package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.data.GameRepository;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.round.application.RoundServiceInterface;
import nl.hu.vkbep.lingo.round.domain.Round;
import nl.hu.vkbep.lingo.word.application.WordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements GameServiceInterface {



    private GameRepository gameRepository;

    private WordServiceInterface wordServiceInterface;

    private RoundServiceInterface roundServiceInterface;

    public GameService(GameRepository gameRepository, WordServiceInterface wordServiceInterface, RoundServiceInterface roundServiceInterface) {
        this.gameRepository = gameRepository;
        this.wordServiceInterface = wordServiceInterface;
        this.roundServiceInterface = roundServiceInterface;
    }

    @Override
    public void getAllGames() {

    }

    public Game createNewGame(){

        //CREATE NEW GAME
        Game game = new Game();

        //SET RANDOM WORD IN GAME
        game.setWord(wordServiceInterface.getRandomWord());

        //SET GAMESTATUS
        game.setGameStatus(GameStatus.NOTSTARTED);

        //SET GAMETYPE
        game.setGameType(GameType.LETTEROF5);

        //SET ROUND
        game.setRound(roundServiceInterface.createNewRound());

        //SET WORD








        //SAVE GAME
        gameRepository.save(game);

        return game;
    }



    public void startNewRound() {
        Round round = new Round();
        Game game = new Game();

        if (game.getGameStatus() == GameStatus.NOTSTARTED) {
        }
    }
}

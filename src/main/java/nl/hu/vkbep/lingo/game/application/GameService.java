package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.data.GameRepository;
import nl.hu.vkbep.lingo.round.domain.Round;
import nl.hu.vkbep.lingo.word.application.WordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements GameServiceInterface {

    @Autowired
    private GameRepository gameRepository;

    private WordServiceInterface wordServiceInterface;

    private Game game;

    @Override
    public void getAllGames() {

    }

    public Game createNewGame(){

        //CREATE NEW GAME
        Game game = new Game();

        //SET RANDOM WORD IN GAME
        game.setWord(wordServiceInterface.getRandomWord());

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

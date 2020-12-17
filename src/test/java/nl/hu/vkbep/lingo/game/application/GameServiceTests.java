package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.data.GameRepository;
import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.round.application.RoundServiceInterface;
import nl.hu.vkbep.lingo.round.domain.Round;
import nl.hu.vkbep.lingo.round.domain.RoundStatus;
import nl.hu.vkbep.lingo.round.domain.RoundType;
import nl.hu.vkbep.lingo.word.application.WordServiceInterface;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GameServiceTests {

    private final String guess = "tests";
    private final Word word = new Word(1L, "tests");
    private final Game game1 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 0, 1);
    private final Game game2 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 0, 5);
    private final Game game3 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF6, word, 0, 0);
    private final Game game4 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF7, word, 0, 0);
    private final Round round1 = new Round(RoundStatus.NOTCORRECT, RoundType.LETTEROF5, game1, guess);
    private final Round round2 = new Round(RoundStatus.NOTCORRECT, RoundType.LETTEROF5, game1, guess);


    @Test
    @DisplayName("GIVES MAP BACK IF GAME IS CREATED")
    public void createNewGame() {

        GameType gameType = GameType.LETTEROF5;
        Word word = new Word(1L, "tests");

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        RoundServiceInterface roundServiceInterface = mock(RoundServiceInterface.class);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface);
        //Map<String, ? extends Serializable> feedback = Map.of("gameid", 1L, "feedback", word.getWord());

        when(wordServiceInterface.getRandomWord(gameType)).thenReturn(word);
        Map result = gameService.createNewGame();

        //assertEquals(feedback, result);

        assertTrue(result.containsValue(word.getWord()) && result.containsKey("feedback"));

    }

//    @Test
//    @DisplayName("GIVES FEEDBACK RESULT ON GUESSES")
//    public void feedbackResultGuess() {
//        String guess = "tests";
//
//        GameRepository gameRepository = mock(GameRepository.class);
//        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
//        RoundServiceInterface roundServiceInterface = mock(RoundServiceInterface.class);
//
//        when(gameRepository.getById(anyLong())).thenReturn(game1);
//
//        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface);
//
//        Map result = gameService.guess(game1.getId(), guess);
//
//        assertTrue(result.containsValue("feedback"));
//    }




    @Test
    @DisplayName("GIVES BACK A NEW GUESS ATTEMPT")
    public void checkRoundCountPerGame() {

        Map<String, String> feedback = Map.of("Game", game1.toString());

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        RoundServiceInterface roundServiceInterface = mock(RoundServiceInterface.class);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface);

        Map result = gameService.checkRoundCountPerGame(game1, word.getId(), guess);

        assertEquals(feedback, result);
    }

    @Test
    @DisplayName("GIVES BACK MAX ROUND REACHED IF TOO MANY GUESSES")
    public void feedbackMaxRound() {

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        RoundServiceInterface roundServiceInterface = mock(RoundServiceInterface.class);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface);


        //Map feedback = Map.of("feedback", "MAX ROUND REACHED", "gamestatus", GameStatus.ENDED);
//        Map feedback = new HashMap();
//        feedback.put("feedback", "MAX ROUND REACHED");
//        feedback.put("gamestatus", GameStatus.ENDED);

        Map result = gameService.checkRoundCountPerGame(game2, word.getId(), guess);

        //assertEquals(feedback, result);

        assertTrue(result.containsValue("MAX ROUND REACHED"));

    }

    @Test
    @DisplayName("GIVES NEW GAME OF LETTEROF6 IF GAME IS WON")
    public void letterOf6Check() {
        Word word = new Word(1L, "tests");
        Map<String, String> feedback = Map.of("note", "CORRECT");

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        RoundServiceInterface roundServiceInterface = mock(RoundServiceInterface.class);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface);

        when(wordServiceInterface.getRandomWord(GameType.LETTEROF6)).thenReturn(word);
        Game result = gameService.checkGameType(feedback, game1);

        //assertEquals(game3, result);

        assertSame(result.getGameType(), game3.getGameType());
    }

    @Test
    @DisplayName("GIVES NEW GAME OF LETTEROF7 IF GAME IS WON")
    public void letterOf7Check() {
        Word word = new Word(1L, "tests");
        Map<String, String> feedback = Map.of("note", "CORRECT");

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        RoundServiceInterface roundServiceInterface = mock(RoundServiceInterface.class);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface);

        when(wordServiceInterface.getRandomWord(GameType.LETTEROF7)).thenReturn(word);
        Game result = gameService.checkGameType(feedback, game3);

        //assertEquals(game3, result);

        assertSame(result.getGameType(), game4.getGameType());
    }
}

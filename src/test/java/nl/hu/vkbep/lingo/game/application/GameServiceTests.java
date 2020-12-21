package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.data.GameRepository;
import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.round.application.RoundServiceInterface;
import nl.hu.vkbep.lingo.round.domain.Round;
import nl.hu.vkbep.lingo.round.domain.RoundStatus;
import nl.hu.vkbep.lingo.round.domain.RoundType;
import nl.hu.vkbep.lingo.score.application.ScoreService;
import nl.hu.vkbep.lingo.word.application.WordServiceInterface;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GameServiceTests {

    private final String guess = "tests";
    private static final Word word = new Word(1L, "tests");
    private static final Game game1 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 0, 1);
    private static final Game game2 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 0, 5);
    private static final Game game3 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF6, word, 0, 0);
    private static final Game game4 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF7, word, 0, 0);
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
        ScoreService scoreService = mock(ScoreService.class);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface, scoreService);
        //Map<String, ? extends Serializable> feedback = Map.of("gameid", 1L, "feedback", word.getWord());

        when(wordServiceInterface.getRandomWord(gameType)).thenReturn(word);
        Map result = gameService.createNewGame();

        //assertEquals(feedback, result);

        assertTrue(result.containsValue(word.getWord()) && result.containsKey("feedback"));

    }

    @Test
    @DisplayName("GIVES FEEDBACK RESULT ON GUESSES")
    public void feedbackResultGuess(){
        Long playerid = 1L;
        Game game = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 0, 1);

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        RoundServiceInterface roundServiceInterface = mock(RoundServiceInterface.class);
        ScoreService scoreService = mock(ScoreService.class);

        when(gameRepository.getById(game.getWord().getId())).thenReturn(game);
        when(gameRepository.getById(game.getId())).thenReturn(game);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface, scoreService);

        Map result = gameService.guess(game.getId(), guess);

        assertTrue(result.containsValue(game.toString()));

    }



    @Test
    @DisplayName("GIVES BACK A NEW GUESS ATTEMPT")
    public void checkRoundCountPerGame() {

        Long playerid = 1L;
        Map<String, String> feedback = Map.of("Game", game1.toString());

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        RoundServiceInterface roundServiceInterface = mock(RoundServiceInterface.class);
        ScoreService scoreService = mock(ScoreService.class);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface, scoreService);

        Map result = gameService.checkRoundCountPerGame(game1, word.getId(), guess);

        assertEquals(feedback, result);
    }

    @Test
    @DisplayName("GIVES BACK MAX ROUND REACHED IF TOO MANY GUESSES")
    public void feedbackMaxRound() {
        Long playerid = 1L;

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        RoundServiceInterface roundServiceInterface = mock(RoundServiceInterface.class);
        ScoreService scoreService = mock(ScoreService.class);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface, scoreService);


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
        ScoreService scoreService = mock(ScoreService.class);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface, scoreService);

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
        ScoreService scoreService = mock(ScoreService.class);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface, scoreService);

        when(wordServiceInterface.getRandomWord(GameType.LETTEROF7)).thenReturn(word);
        Game result = gameService.checkGameType(feedback, game3);

        //assertEquals(game3, result);

        assertSame(result.getGameType(), game4.getGameType());
    }

    @Test
    @DisplayName("GIVES GAME BACK IF ID HAS BEEN FOUND")
    void getById() {
        Game game = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 0, 1);

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        RoundServiceInterface roundServiceInterface = mock(RoundServiceInterface.class);
        ScoreService scoreService = mock(ScoreService.class);

        when(gameRepository.getById(game.getId())).thenReturn(game);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface, scoreService);

        Game result = gameService.getById(game.getId());

        assertEquals(game, result);
    }


    private static Stream<Arguments> provideGameintAndResult() {
        return Stream.of(
                Arguments.of(game1, 5),
                Arguments.of(game3, 6),
                Arguments.of(game4, 7)

        );
    }



    @ParameterizedTest
    @MethodSource("provideGameintAndResult")
    @DisplayName("GIVES INT BACK CORRESPONDING WITH GAMETYPE")
    void gameType(Game game, int integer) {



        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        RoundServiceInterface roundServiceInterface = mock(RoundServiceInterface.class);
        ScoreService scoreService = mock(ScoreService.class);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, roundServiceInterface, scoreService);

        int result = gameService.gameType(game);

        assertEquals(integer, result);
    }
}

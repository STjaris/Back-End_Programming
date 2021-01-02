package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.data.GameRepository;
import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.game.exception.GameFinished;
import nl.hu.vkbep.lingo.game.exception.MaxRoundReached;
import nl.hu.vkbep.lingo.gameSession.application.GameSessionService;
import nl.hu.vkbep.lingo.gameSession.domain.GameSession;
import nl.hu.vkbep.lingo.player.domain.Player;
import nl.hu.vkbep.lingo.word.application.WordServiceInterface;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class GameServiceTests {

    private final String guess = "tests";
    private static final Word word = new Word(1L, "tests");
    private static final Game game1 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 0, 1, 0);
    private static final Game game2 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 0, 5, 0);
    private static final Game game3 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF6, word, 0, 0, 0);
    private static final Game game4 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF7, word, 0, 0, 0);
    private static final Game game5 = new Game();

    @Test
    @DisplayName("GIVES MAP BACK IF GAME IS CREATED")
    void createNewGame() {
        Word word1 = new Word(1L, "baard");
        Player player = new Player("test", "test", "test", true, false);
        Game game = new Game(1L, GameStatus.NOTSTARTED, GameType.LETTEROF5, word1);

        List<Game> listOfgames = List.of(game);
        GameSession gameSession = new GameSession(listOfgames, player, 0);
        Long playerid = 1L;

        //Map expectedMap = Map.of("gameid", game.getId(), "feedback", game.getWord().getWord());

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        GameSessionService gameSessionService = mock(GameSessionService.class);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, gameSessionService);

        when(gameService.createGame(GameType.LETTEROF5)).thenReturn(game);
        when(gameSessionService.createNewGameSession(game, playerid)).thenReturn(gameSession);
        when(wordServiceInterface.getRandomWord(game.getGameType())).thenReturn(word1);

        Map result = gameService.createNewGame(playerid);

        //assertEquals(expectedMap, result);
        assertTrue(result.containsKey("feedback"));
        assertTrue(result.containsValue(word1.getWord()));
    }

    private static Stream<Arguments> provideGameAndExpectedGameType() {
        return Stream.of(
                Arguments.of(game1, GameType.LETTEROF6),
                Arguments.of(game3, GameType.LETTEROF7)
        );
    }

    @Test
    @DisplayName("RETURNS GAME IF ONE IS CREATED")
    void createGame() {
        Word word = new Word(1L, "baard");
        Game game = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word);

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        GameSessionService gameSessionService = mock(GameSessionService.class);

        when(wordServiceInterface.getRandomWord(game.getGameType())).thenReturn(word);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, gameSessionService);

        Game result = gameService.createGame(GameType.LETTEROF5);

        assertEquals(game.getGameType(), result.getGameType());
    }

    @Test
    @DisplayName("RETURNS CORRECT IF WORD IS GUESSED")
    void makeCorrectAttempt() {
        Game game = new Game(1L, GameStatus.NOTSTARTED, GameType.LETTEROF5, word);

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        GameSessionService gameSessionService = mock(GameSessionService.class);


        when(gameRepository.getById(game.getId())).thenReturn(game);
        when(wordServiceInterface.attemptChecker(word.getId(), word.getWord())).thenReturn(true);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, gameSessionService);
        Map result = gameService.makeAttempt(game.getId(), word.getWord());

        assertTrue(result.containsValue("CORRECT"));

    }

    @Test
    @DisplayName("RETURNS FEEDBACK IF WORD IS GUESSED BUT NOT CORRECT")
    void makeAttempt() {
        Game game = new Game(1L, GameStatus.NOTSTARTED, GameType.LETTEROF5, word);
        Word attempt = new Word(2L, "baard");

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        GameSessionService gameSessionService = mock(GameSessionService.class);

        when(gameRepository.getById(game.getId())).thenReturn(game);
        when(wordServiceInterface.attemptChecker(word.getId(), word.getWord())).thenReturn(true);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, gameSessionService);
        Map result = gameService.makeAttempt(game.getId(), attempt.getWord());

        assertTrue(result.containsKey("FEEDBACK"));

    }

    @Test
    @DisplayName("RETURNS MAXROUNDREACHED IF WORD IS GUESSED BUT NOT CORRECT")
    void makeMoreThenMaxAttempt() {
        Word attempt = new Word(2L, "baard");

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        GameSessionService gameSessionService = mock(GameSessionService.class);

        when(gameRepository.getById(game2.getId())).thenReturn(game2);
        when(wordServiceInterface.attemptChecker(word.getId(), word.getWord())).thenReturn(true);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, gameSessionService);
        Map result = gameService.makeAttempt(game2.getId(), attempt.getWord());

        assertTrue(result.containsValue(new MaxRoundReached(game2.getId()).getMessage()));
    }

    @ParameterizedTest
    @MethodSource("provideGameAndExpectedGameType")
    @DisplayName("CHECKS THE GAMETYPE AND RETURNS NEW GAME")
    void checkGameType(Game game, GameType gameType) {
        //Game game = new Game(1L, GameStatus.NOTSTARTED, GameType.LETTEROF5, word);

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        GameSessionService gameSessionService = mock(GameSessionService.class);

        when(gameRepository.getById(game.getId())).thenReturn(game);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, gameSessionService);

        Game result = gameService.checkGameType(game.getId());

        assertTrue(result.getGameType() == gameType);
    }

    @Test
    @DisplayName("THROWS GAMEFINISHED EXCEPTION IF GAME IS FINISHED")
    void checkGameTypeGameFinished() {

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        GameSessionService gameSessionService = mock(GameSessionService.class);

        when(gameRepository.getById(game4.getId())).thenReturn(game4);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, gameSessionService);

        assertThrows(GameFinished.class, () -> {
            gameService.checkGameType(game4.getId());
        });
    }

    @Test
    @DisplayName("RETURNS GAME IF NO GAMETYPE FOUND")
    void checkGameTypeReturnGame() {
        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        GameSessionService gameSessionService = mock(GameSessionService.class);

        when(gameRepository.getById(game5.getId())).thenReturn(game5);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, gameSessionService);

        Game result = gameService.checkGameType(game5.getId());

        assertEquals(game5, result);
    }

    @Test
    @DisplayName("GIVES GAME BACK IF ID HAS BEEN FOUND")
    void getById() {
        Game game = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 0, 1, 0);

        GameRepository gameRepository = mock(GameRepository.class);
        WordServiceInterface wordServiceInterface = mock(WordServiceInterface.class);
        GameSessionService gameSessionService = mock(GameSessionService.class);

        when(gameRepository.getById(game.getId())).thenReturn(game);

        GameService gameService = new GameService(gameRepository, wordServiceInterface, gameSessionService);

        Game result = gameService.getById(game.getId());

        assertEquals(game, result);
    }
}

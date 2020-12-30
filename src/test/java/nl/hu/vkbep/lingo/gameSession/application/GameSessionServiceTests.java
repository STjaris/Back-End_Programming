package nl.hu.vkbep.lingo.gameSession.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.gameSession.data.GameSessionRepository;
import nl.hu.vkbep.lingo.gameSession.domain.GameSession;
import nl.hu.vkbep.lingo.player.data.PlayerRepository;
import nl.hu.vkbep.lingo.player.domain.Player;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameSessionServiceTests {


    @Test
    @DisplayName("CREATE NEW GAMESESSION")
    void createNewGameSession() {
        Player player = new Player("test", "test", "test", true , false);
        Long playerid = 1L;
        Word word = new Word(1L, "tests");
        Game game = new Game(1L, GameStatus.NOTSTARTED, GameType.LETTEROF5, word);
        List<Game> list = List.of(game);

        GameSession expectedGameSession = new GameSession(list, player, 0);

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        GameSessionRepository gameSessionRepository = mock(GameSessionRepository.class);

        when(playerRepository.getPlayerById(playerid)).thenReturn(player);

        GameSessionService gameSessionService = new GameSessionService(
                playerRepository, gameSessionRepository);

        GameSession result = gameSessionService.createNewGameSession(game, playerid);

        assertEquals(expectedGameSession.getGames(), result.getGames());
        assertEquals(expectedGameSession.getPlayer(), result.getPlayer());
    }

    @Test
    @DisplayName("UPDATES GAMESESSION")
    void updateGameSession() {
        Player player = new Player("test", "test", "test", true , false);

        Word word = new Word(1L, "tests");

        Game game1 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 5, 0, 25);
        Game game2 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 5, 0, 30);

        List<Game> startList = new ArrayList<>();
        startList.add(game1);
        List<Game> expectedList = List.of(game1, game2);

        GameSession startGameSession = new GameSession(startList, player, 0);
        GameSession expectedGameSession = new GameSession(expectedList, player, 0);

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        GameSessionRepository gameSessionRepository = mock(GameSessionRepository.class);


        GameSessionService gameSessionService = new GameSessionService(
                playerRepository, gameSessionRepository);

        GameSession result = gameSessionService.updateGameSession(startGameSession, game2, game1);

        assertEquals(expectedGameSession.getGames(), result.getGames());
        assertEquals(expectedGameSession.getPlayer(), result.getPlayer());

    }

    @Test
    @DisplayName("RETURNS GAMESESSION IF GAMESESSION CONTAINS GAME")
    void getGameSessionContainingGame() {
        Player player = new Player("test", "test", "test", true , false);

        Word word = new Word(1L, "tests");
        Game game1 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 5, 0, 25);
        List<Game> startList = List.of(game1);
        GameSession expectedGameSession = new GameSession(startList, player, 0);

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        GameSessionRepository gameSessionRepository = mock(GameSessionRepository.class);

        when(gameSessionRepository.getGameSessionByGamesIsContaining(game1)).thenReturn(expectedGameSession);

        GameSessionService gameSessionService = new GameSessionService(
                playerRepository, gameSessionRepository);

        GameSession result = gameSessionService.getGameSessionContainingGame(game1);

        assertEquals(expectedGameSession, result);
    }

    @Test
    @DisplayName("GIVES A LIST WITH SCORES BACK")
    public void getHighscore() {

        Word word = new Word(1L, "tests");
        Player player = new Player("test", "test", "test", true, false);
        Game game = new Game(1L, GameStatus.NOTSTARTED, GameType.LETTEROF5, word);

        List<Game> games = List.of(game);
        GameSession gameSession1 = new GameSession(games, player, 25);
        GameSession gameSession2 = new GameSession(games, player, 50);
        List<GameSession> gameSessions = List.of(gameSession2, gameSession1);

        Map map1 = Map.of(
                "id", 0,
                "score", gameSession2.getTotalScore(),
                "player", gameSession2.getPlayer().getName()
        );

        Map map2 = Map.of(
                "id", 1,
                "score", gameSession1.getTotalScore(),
                "player", gameSession1.getPlayer().getName()
        );

        List<Map> listOfMaps = List.of(map1, map2);

        PlayerRepository playerRepository = mock(PlayerRepository.class);
        GameSessionRepository gameSessionRepository = mock(GameSessionRepository.class);

        when(gameSessionRepository.getAllOrderByScore()).thenReturn(gameSessions);

        GameSessionService gameSessionService = new GameSessionService(
                playerRepository, gameSessionRepository);

        List<Map> result = gameSessionService.getHighscore();

        assertEquals(listOfMaps, result);

    }
}

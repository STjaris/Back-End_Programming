package nl.hu.vkbep.lingo.gameSession.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.gameSession.data.GameSessionRepository;
import nl.hu.vkbep.lingo.gameSession.domain.GameSession;
import nl.hu.vkbep.lingo.player.data.PlayerRepository;
import nl.hu.vkbep.lingo.player.domain.Player;
import nl.hu.vkbep.lingo.score.application.ScoreService;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        ScoreService scoreService = mock(ScoreService.class);

        when(playerRepository.getPlayerById(playerid)).thenReturn(player);

        GameSessionService gameSessionService = new GameSessionService(
                playerRepository, gameSessionRepository, scoreService);

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
        ScoreService scoreService = mock(ScoreService.class);

        GameSessionService gameSessionService = new GameSessionService(
                playerRepository, gameSessionRepository, scoreService);

        GameSession result = gameSessionService.updateGameSession(startGameSession, game2, game1);

        assertEquals(expectedGameSession.getGames(), result.getGames());
        assertEquals(expectedGameSession.getPlayer(), result.getPlayer());

    }
}

package nl.hu.vkbep.lingo.score.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.player.data.PlayerRepository;
import nl.hu.vkbep.lingo.player.domain.Player;
import nl.hu.vkbep.lingo.score.data.ScoreRepository;
import nl.hu.vkbep.lingo.score.domain.Score;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ScoreServiceTests {

    @Test
    @DisplayName("GIVES A LIST WITH SCORES BACK")
    public void getHighscore() {

        Player player = new Player("test", "test", "test", true, false);
        Long gameid = 1L;
        Score score = new Score(40.0, player, gameid);
        Map scoreMap = Map.of("id", 1, "score",score.getScore(), "player", score.getPlayer().getName());
        List<Score> scoreList = List.of(score);
        List<Map> resultList = List.of(scoreMap);


        ScoreRepository scoreRepository = mock(ScoreRepository.class);
        PlayerRepository playerRepository = mock(PlayerRepository.class);

        when(scoreRepository.getAllOrderByScore()).thenReturn(scoreList);

        ScoreService highScoreService = new ScoreService(scoreRepository, playerRepository);

        List<Map> result = highScoreService.getHighscore();

        assertEquals(resultList, result);

    }

    @Test
    @DisplayName("CALCULATES SCORE")
    public void calculateScores(){
        Player player = new Player("test", "test", "test", true, false);
        Long playerid = 1L;
        List<Long> list = List.of(1L, 2L);
        int multiplier  = 5;

        Word word = new Word(1L, "tests");
        Score score = new Score(50, player, list.get(0));
        Game game = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 0 ,1);

        ScoreRepository scoreRepository = mock(ScoreRepository.class);
        PlayerRepository playerRepository = mock(PlayerRepository.class);

        when(playerRepository.getPlayerById(playerid)).thenReturn(player);

        ScoreService scoreService = new ScoreService(scoreRepository, playerRepository);
        Score result = scoreService.calculateScores(game, multiplier, playerid, list);

        assertEquals(score.getScore(), result.getScore());
    }
}

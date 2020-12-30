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

//    @Test
//    @DisplayName("GIVES A LIST WITH SCORES BACK")
//    public void getHighscore() {
//
//        Player player = new Player("test", "test", "test", true, false);
//        Long gameid = 1L;
//        Score score = new Score(40.0, player, gameid);
//        Map scoreMap = Map.of("id", 1, "score",score.getScore(), "player", score.getPlayer().getName());
//        List<Score> scoreList = List.of(score);
//        List<Map> resultList = List.of(scoreMap);
//
//
//        ScoreRepository scoreRepository = mock(ScoreRepository.class);
//        PlayerRepository playerRepository = mock(PlayerRepository.class);
//
//        when(scoreRepository.getAllOrderByScore()).thenReturn(scoreList);
//
//        ScoreService scoreService = new ScoreService(scoreRepository, playerRepository);
//
//        List<Map> result = scoreService.getHighscore();
//
//        assertEquals(resultList, result);
//
//    }

    @Test
    @DisplayName("CALCULATES SCORE")
    void calculation() {
        int roundcount = 0;
        int multiplier = 5;
        double expectedResult = 25;

        ScoreRepository scoreRepository = mock(ScoreRepository.class);
        PlayerRepository playerRepository = mock(PlayerRepository.class);

        ScoreService scoreService = new ScoreService(scoreRepository, playerRepository);

        double result = scoreService.calculation(roundcount, multiplier);

        assertEquals(expectedResult, result);

    }
}

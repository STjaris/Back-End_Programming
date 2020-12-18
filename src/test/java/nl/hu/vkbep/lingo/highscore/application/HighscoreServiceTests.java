package nl.hu.vkbep.lingo.highscore.application;

import nl.hu.vkbep.lingo.highScore.application.HighScoreService;
import nl.hu.vkbep.lingo.highScore.data.HighScoreRepository;
import nl.hu.vkbep.lingo.player.domain.Player;
import nl.hu.vkbep.lingo.score.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class HighscoreServiceTests {


    @Test
    @DisplayName("GIVES A LIST WITH SCORES BACK")
    public void getHighscore() {

        Player player = new Player("test", "test", "test", true, false);
        Score score = new Score(1L, 40.0, player);
        Map<String, Score> scoreMap = Map.of("highscore", score);

        HighScoreRepository highScoreRepository = Mockito.mock(HighScoreRepository.class);

        when(highScoreRepository.getAllByScoreOrderByScoreScoreAsc()).thenReturn(scoreMap);

        HighScoreService highScoreService = new HighScoreService(highScoreRepository);

        Map result = highScoreService.getHighscore();

        assertEquals(scoreMap, result);

    }
}

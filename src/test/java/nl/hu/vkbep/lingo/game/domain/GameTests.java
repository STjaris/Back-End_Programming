package nl.hu.vkbep.lingo.game.domain;

import nl.hu.vkbep.lingo.player.data.PlayerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class GameTests {

    @Test
    @DisplayName("CALCULATES SCORE")
    void calculation() {
        int roundcount = 0;
        int multiplier = 5;
        double expectedResult = 25;

        Game game = new Game();

        double result = game.calculation(roundcount, multiplier);

        assertEquals(expectedResult, result);

    }
}

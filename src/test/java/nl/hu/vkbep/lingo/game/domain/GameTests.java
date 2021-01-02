package nl.hu.vkbep.lingo.game.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@State(Scope.Benchmark)
public class GameTests {

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Test
    @DisplayName("CALCULATES SCORE")
    public void calculation() {
        int roundcount = 0;
        int multiplier = 5;
        double expectedResult = 25;

        Game game = new Game();

        double result = game.calculation(roundcount, multiplier);

        assertEquals(expectedResult, result);

    }
}

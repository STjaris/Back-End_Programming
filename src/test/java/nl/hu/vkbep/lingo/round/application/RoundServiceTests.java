package nl.hu.vkbep.lingo.round.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.round.domain.Round;
import nl.hu.vkbep.lingo.round.domain.RoundStatus;
import nl.hu.vkbep.lingo.round.domain.RoundType;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class RoundServiceTests {

    private static final Word word = new Word(1L, "tests");

    private static final Game game1 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word);
    private static final Game game2 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF6, word);
    private static final Game game3 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF7, word);

    private static final Round round1 = new Round(RoundStatus.NOTCORRECT, RoundType.LETTEROF5, game1, "tests");
    private static final Round round2 = new Round(RoundStatus.NOTCORRECT, RoundType.LETTEROF6, game2, "tests");
    private static final Round round3 = new Round(RoundStatus.NOTCORRECT, RoundType.LETTEROF7, game2, "tests");

    private static Stream<Arguments> provideGameAndResult() {
        return Stream.of(
                Arguments.of(game1, round1, true),
                Arguments.of(game1, round2, false),
                Arguments.of(game1, round3, false),

                Arguments.of(game2, round1, false),
                Arguments.of(game2, round2, true),
                Arguments.of(game2, round3, false),

                Arguments.of(game3, round1, false),
                Arguments.of(game3, round2, false),
                Arguments.of(game3, round3, true)
        );
    }

    private static Stream<Arguments> provideRoundLengthAndResult() {
        return Stream.of(
                Arguments.of(round1, 5, true),
                Arguments.of(round2, 5, false),
                Arguments.of(round3, 5, false),

                Arguments.of(round1, 6, false),
                Arguments.of(round2, 6, true),
                Arguments.of(round3, 6, false),

                Arguments.of(round1, 7, false),
                Arguments.of(round2, 7, false),
                Arguments.of(round3, 7, true)
        );
    }

    private static Stream<Arguments> provideGameGuessRoundAndResult() {
        return Stream.of(
                Arguments.of(game1, "tests", round1, true),
                Arguments.of(game1, "guess", round1, false)

        );
    }


    @Before
    public void init() {

        word.setWord("tests");
//
//        game1 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word);
//        game2 = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF6, word);
//
//        round1 = new Round(RoundStatus.NOTCORRECT, RoundType.LETTEROF5, game1, "tests");
//        round2 = new Round(RoundStatus.NOTCORRECT, RoundType.LETTEROF6, game2, "tests");
    }

    @ParameterizedTest
    @MethodSource("provideGameGuessRoundAndResult")
    public void wordCheck(Game game, String guess, Round round, boolean expectedResult){
        RoundService roundService = new RoundService();

        boolean result = roundService.wordCheck(game.getWord().getWord(), guess, round).containsValue("CORRECT");

        Assertions.assertEquals(result, expectedResult);
    }




    @ParameterizedTest
    @MethodSource("provideGameAndResult")
    public void roundCheckFromGame(Game game, Round round, boolean expectedResult) {

        RoundService roundServiceMock = Mockito.mock(RoundService.class);
        RoundService roundService = new RoundService();

        boolean result = roundService.roundCheckFromGame(game).equals(round.getRoundType());

        assertEquals(result, expectedResult);
    }


    @ParameterizedTest
    @MethodSource("provideRoundLengthAndResult")
    public void roundTypeCheck(Round round, int length, boolean expectedResult) {

        RoundService roundService = new RoundService();

        Boolean result = roundService.roundTypeCheck(round) == length;

        assertEquals(result, expectedResult);

    }
}

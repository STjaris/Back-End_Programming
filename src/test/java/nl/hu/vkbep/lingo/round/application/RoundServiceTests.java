package nl.hu.vkbep.lingo.round.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.round.data.RoundRepository;
import nl.hu.vkbep.lingo.round.domain.Round;
import nl.hu.vkbep.lingo.round.domain.RoundStatus;
import nl.hu.vkbep.lingo.round.domain.RoundType;
import nl.hu.vkbep.lingo.word.application.WordServiceInterface;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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

    private static Stream<Arguments> provideWordGuessRoundAndResult() {
        return Stream.of(
                Arguments.of(word.getWord(), "tests", round1, true),
                Arguments.of(word.getWord(), "gues", round1, false)

        );
    }

    @Before
    public void init() {

        word.setWord("tests");
    }

    @Test
    @DisplayName("gives error when wordlength not correct")
    public void wordLengthCheck() {
        String guess = "qwert";


        WordServiceInterface wordService = mock(WordServiceInterface.class);
        RoundRepository roundRepository = mock(RoundRepository.class);

        when(wordService.wordLengthCheck(eq(guess), anyInt()))
                .thenReturn(false);

        RoundService roundService = new RoundService(wordService, roundRepository);

        boolean result = roundService.checkWord(word.getWord(), guess, round1)
                .containsValue("INPUT NOT VALID, WORD LENGTH NOT CORRECT");

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("gives correct when word is guessed")
    public void wordGuess(){
        String guess = "qwert";

        WordServiceInterface wordService = mock(WordServiceInterface.class);
        RoundRepository roundRepository = mock(RoundRepository.class);

        when(wordService.wordLengthCheck(eq(guess), anyInt()))
                .thenReturn(true);
        when(wordService.wordCheck(guess, word.getWord()))
                .thenReturn(true);

        RoundService roundService = new RoundService(wordService, roundRepository);

        Map result = roundService.checkWord(word.getWord(), guess, round1);

        Assertions.assertTrue(result.containsValue("CORRECT"));
    }

    @Test
    @DisplayName("gives feedback when word has correct length but not guessed")
    public void wordFeedback(){
        String guess = "qwert";
        Map<String, List<String>> feedback = Map.of("feedback", List.of("ABSENT", "ABSENT"));

        WordServiceInterface wordService = mock(WordServiceInterface.class);
        RoundRepository roundRepository = mock(RoundRepository.class);

        when(wordService.wordLengthCheck(eq(guess), anyInt()))
                .thenReturn(true);
        when(wordService.wordCheck(guess, word.getWord()))
                .thenReturn(false);
        when(wordService.letterCheck(guess, word.getWord()))
                .thenReturn(feedback);

        RoundService roundService = new RoundService(wordService, roundRepository);

        Map result = roundService.checkWord(word.getWord(), guess, round1);

        Assertions.assertEquals(feedback, result);
    }




//    @ParameterizedTest
//    @MethodSource("provideGameGuessRoundAndResult")
//    public void wordCheck(Game game, String guess, Round round, boolean expectedResult) {
//        RoundService roundService = new RoundService();
//
//        boolean result = roundService.wordCheck(game.getWord().getWord(), guess, round)
//                .containsValue("CORRECT");
//
//        Assertions.assertEquals(result, expectedResult);
//    }


//    @ParameterizedTest
//    @MethodSource("provideGameAndResult")
//    public void roundCheckFromGame(Game game, Round round, boolean expectedResult) {
//
//        RoundService roundServiceMock = Mockito.mock(RoundService.class);
//        RoundService roundService = new RoundService();
//
//        boolean result = roundService.roundCheckFromGame(game)
//                .equals(round.getRoundType());
//
//        assertEquals(result, expectedResult);
//    }
//
//
//    @ParameterizedTest
//    @MethodSource("provideRoundLengthAndResult")
//    public void roundTypeCheck(Round round, int length, boolean expectedResult) {
//
//        RoundService roundService = new RoundService();
//
//        Boolean result = roundService.roundTypeCheck(round) == length;
//
//        assertEquals(result, expectedResult);
//
//    }
}

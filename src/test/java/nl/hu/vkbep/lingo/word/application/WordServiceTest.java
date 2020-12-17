package nl.hu.vkbep.lingo.word.application;

import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.word.data.WordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@DisplayName("WordServiceTest")
public class WordServiceTest {

    private static Stream<Arguments> provideWordLengthAndResult() {
        return Stream.of(
                Arguments.of("Test", 5, false),
                Arguments.of("isTeLang", 5, false),
                Arguments.of("qwerty", 6, true),
                Arguments.of("qwertyu", 7, true),
                Arguments.of("qwertyu", 5, false)
        );
    }

    private static Stream<Arguments> provideWordGuessAndResult() {
        return Stream.of(
                Arguments.of("Tests", "Tests", true),
                Arguments.of("isTeLang", "isTeLang", true),
                Arguments.of("qwerty", "zxcvbv", false),
                Arguments.of("qwertyu", "adsfgg", false),
                Arguments.of("qwertyu", "qwertyu", true)
        );
    }

    private static Stream<Arguments> provideIdWordAndResult() {
        return Stream.of(
                Arguments.of("aagje", true),
                Arguments.of("aaibaar", true),
                Arguments.of("zxcvbv", false),
                Arguments.of("adsfgg", false),
                Arguments.of("qwertyu", false)
        );
    }

    private static Stream<Arguments> provideGametypeLengthAndResult() {
        return Stream.of(
                Arguments.of(GameType.LETTEROF5, 5, true),
                Arguments.of(GameType.LETTEROF5, 6, false),
                Arguments.of(GameType.LETTEROF5, 7, false),

                Arguments.of(GameType.LETTEROF6, 5, false),
                Arguments.of(GameType.LETTEROF6, 6, true),
                Arguments.of(GameType.LETTEROF6, 7, false),

                Arguments.of(GameType.LETTEROF7, 5, false),
                Arguments.of(GameType.LETTEROF7, 6, false),
                Arguments.of(GameType.LETTEROF7, 7, true)
        );
    }


    @ParameterizedTest
    @MethodSource("provideWordLengthAndResult")
    public void wordLengthCheckTest(String input, int length, boolean expectedResult) {
        WordRepository wordRepository = Mockito.mock(WordRepository.class);
        WordService wordService = new WordService(wordRepository);

        boolean result = wordService.wordLengthCheck(input, length);

        Assertions.assertEquals(result, expectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideWordGuessAndResult")
    public void wordCheckTest(String input, String word, boolean expectedResult) {
        WordRepository wordRepository = Mockito.mock(WordRepository.class);
        WordService wordService = new WordService(wordRepository);

        boolean result = wordService.wordCheck(input, word);

        Assertions.assertEquals(result, expectedResult);
    }
//
//    @ParameterizedTest
//    @MethodSource("provideIdWordAndResult")
//    public void wordExists(String word, boolean expectedResult) {
//
//        WordService wordService = new WordService();
//
//        boolean result = wordService.wordExits(word);
//
//        assertEquals(result, expectedResult);
//    }


    @ParameterizedTest
    @MethodSource("provideGametypeLengthAndResult")
    public void getLengthByGameType(GameType gameType, int length, boolean expectedresult){
        WordRepository wordRepository = Mockito.mock(WordRepository.class);
        WordService wordService = new WordService(wordRepository);

        boolean result = wordService.getLengthByGameType(gameType) == length;

        Assertions.assertEquals(result, expectedresult);
    }

    private static Stream<Arguments> provideWordsForFeedback() {
        return Stream.of(
           Arguments.of("baard", "bonje", List.of("CORRECT", "ABSENT", "ABSENT", "ABSENT", "ABSENT")),
           Arguments.of("baard", "barst", List.of("CORRECT", "CORRECT", "CONTAINS", "ABSENT", "ABSENT")),
            Arguments.of("baard", "draad", List.of("ABSENT", "CONTAINS", "CORRECT", "CONTAINS", "CORRECT")),
            Arguments.of("baard", "baard", List.of("CORRECT", "CORRECT", "CORRECT", "CORRECT", "CORRECT"))
        );
    }


    @ParameterizedTest
    @MethodSource("provideWordsForFeedback")
    @DisplayName("GIVES FEEDBACK FOR GUESSES")
    void letterCheck(String word, String guess, List<String> expectedFeedback) {

        WordRepository wordRepository = Mockito.mock(WordRepository.class);
        WordService wordService = new WordService(wordRepository);

        Map<String, List<String>> result = wordService.letterCheck(guess, word);

        Assertions.assertEquals(expectedFeedback, result.get("feedback"));
    }


//    @Test
//    @DisplayName("GIVES FEEDBACK WHEN RANDOM PICKED WORD LENGTH = GAMETYPE LENGTH")
//    void getRandomWord() {
//        int i = 5;
//
//        Word word = new Word(1L, "tests");
//        GameType gameType = GameType.LETTEROF5;
//
//        WordRepository wordRepository = Mockito.mock(WordRepository.class);
//        WordService wordService = new WordService(wordRepository);
//
//        //when(wordService.getLengthByGameType();
//
//        when(wordService.getRandomWord(GameType.LETTEROF5)).thenReturn(word);
//
//        boolean result = wordService.getRandomWord(GameType.LETTEROF5).getWord().length() == 5;
//
//        Assertions.assertTrue(result);
//    }
}

package nl.hu.vkbep.lingo.word.wordService;

import nl.hu.vkbep.lingo.word.application.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@DisplayName("WORDSERVICETEST")
public class WordServiceTest {

    private static Stream<Arguments> provideStringAndResults() {
        return Stream.of(
                Arguments.of("Test", false),
                Arguments.of("isTeLang", false),
                Arguments.of("qwert", true),
                Arguments.of("qwerty", true),
                Arguments.of("qwertyu", true)
        );
    }


    @ParameterizedTest
    @MethodSource("provideStringAndResults")
    @DisplayName("CONTENT STRING MATCHES WITH WORD CHECK")
    public void lettercheck(String input, String word, boolean expectedResult) {
        WordService wordService = new WordService();

        wordService.letterCheck(input, word);

        Boolean result = false;

        assertEquals(result, expectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideStringAndResults")
    @DisplayName("DIRECT WORD ON INPUT CHECK")
    public void wordCheck(String input, String word, boolean expectedResult) {
        WordService wordService = new WordService();

        Boolean result = wordService.wordCheck(input, word);

        assertEquals(result, expectedResult);
    }


}

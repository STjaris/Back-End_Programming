package nl.hu.vkbep.lingo.fileImport.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class LingoWordFilterTests {
    private static Stream<Arguments> provideLengthAndResults() {
        return Stream.of(
                Arguments.of("Test", false),
                Arguments.of("isTeLang", false),
                Arguments.of("qwert", true),
                Arguments.of("qwerty", true),
                Arguments.of("qwertyu", true)
        );
    }

    private static Stream<Arguments> provideCharAndResults() {
        return Stream.of(
                Arguments.of("TEST", false),
                Arguments.of("@test", false),
                Arguments.of("123456", false),
                Arguments.of("/,.\\", false),
                Arguments.of("qwerty", true)
        );
    }
        @ParameterizedTest
    @MethodSource("provideLengthAndResults")
    @DisplayName("Is in between 5 and 7")
    public void verifyLengthIsInBetween(String input, boolean expectedResult) {
        LingoWordFilter lingoWordFilter = new LingoWordFilter();

        boolean result = lingoWordFilter.stringFiltering(input);

        Assertions.assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource("provideCharAndResults")
    @DisplayName("Contains no special characters")
    public void verifyCharOfString(String input, boolean expectedResult) {
        LingoWordFilter lingoWordFilter = new LingoWordFilter();

        boolean result = lingoWordFilter.stringFiltering(input);

        Assertions.assertEquals(expectedResult, result);
    }

}

package nl.hu.vkbep.lingo.word.wordService;

import nl.hu.vkbep.lingo.fileImport.presentation.WordProcessorController;
import nl.hu.vkbep.lingo.word.application.WordService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@DisplayName("WordServiceTest")
public class WordServiceTest {

    private static Stream<Arguments> provideWordLengthAndResult() {
        return Stream.of(
                Arguments.of("Test",5,  false),
                Arguments.of("isTeLang", 5, false),
                Arguments.of("qwerty",6,  true),
                Arguments.of("qwertyu",7,  true),
                Arguments.of("qwertyu",5,  false)
        );
    }

    private static Stream<Arguments> provideWordGuessAndResult() {
        return Stream.of(
                Arguments.of("Tests","Tests",  true),
                Arguments.of("isTeLang", "isTeLang", true),
                Arguments.of("qwerty","zxcvbv" , false),
                Arguments.of("qwertyu","adsfgg" ,  false),
                Arguments.of("qwertyu","qwertyu",  true)
        );
    }


    @ParameterizedTest
    @MethodSource("provideWordLengthAndResult")
    public void wordLengthCheckTest(String input, int length, boolean expectedResult){
        WordService wordService = new WordService();

        boolean result = wordService.wordLengthCheck(input, length);

        assertEquals(result, expectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideWordGuessAndResult")
    public void wordCheckTest(String input, String word, boolean expectedResult){
        WordService wordService = new WordService();

        boolean result = wordService.wordCheck(input, word);

        assertEquals(result, expectedResult);
    }


}

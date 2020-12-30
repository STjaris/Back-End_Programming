package nl.hu.vkbep.lingo.fileImport.application;


import nl.hu.vkbep.lingo.fileImport.data.FileWordWriter;
import nl.hu.vkbep.lingo.word.data.WordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DisplayName("FilterWordsProcessorTests")
public class FileWordReaderTests {

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

    @Test
    @DisplayName("RETURNS TRUE IF COUNT == 0")
    void countIsNullTextImport() throws IOException {
        List<String> stringList = List.of("tests", "baard");

        WordRepository wordRepository = mock(WordRepository.class);
        FileWordWriter fileWordWriter = mock(FileWordWriter.class);

        when(wordRepository.count()).thenReturn(0L);
        when(fileWordWriter.arrayFiltering(stringList)).thenReturn(true);

        FileWordReader fileWordReader = new FileWordReader(wordRepository, fileWordWriter);


        boolean result = fileWordReader.startTextImport();

        assertTrue(result);

    }

    //    @ParameterizedTest
//    @MethodSource("provideLengthAndResults")
//    @DisplayName("Is in between 5 and 7")
//    public void verifyLengthIsInBetween(String input, boolean expectedResult) {
//        FilterWordsProcessor filterWordsProcessor = new FilterWordsProcessor();
//
//        boolean result = filterWordsProcessor.stringFilteringOnLength(input);
//
//        Assertions.assertEquals(expectedResult, result);
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideCharAndResults")
//    @DisplayName("Contains no special characters")
//    public void verifyCharOfString(String input, boolean expectedResult) {
//        FilterWordsProcessor filterWordsProcessor = new FilterWordsProcessor();
//
//        boolean result = filterWordsProcessor.stringFilteringOnSpecialChar(input);
//
//        Assertions.assertEquals(expectedResult, result);
//    }
}

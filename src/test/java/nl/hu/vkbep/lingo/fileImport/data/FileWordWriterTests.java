package nl.hu.vkbep.lingo.fileImport.data;

import nl.hu.vkbep.lingo.fileImport.domain.WordFilter;
import nl.hu.vkbep.lingo.word.application.WordService;
import nl.hu.vkbep.lingo.word.data.WordRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class FileWordWriterTests {

    @Test
    @DisplayName("RETURNS TRUE WHEN LINES ARE FILTERED")
    void arrayFiltering() {
        List<String> list = List.of("tests", "baard");

        WordFilter wordFilter = mock(WordFilter.class);
        WordService wordService = mock(WordService.class);

        FileWordWriter fileWordWriter = new FileWordWriter(wordFilter, wordService);

        assertTrue(fileWordWriter.arrayFiltering(list));
    }
}

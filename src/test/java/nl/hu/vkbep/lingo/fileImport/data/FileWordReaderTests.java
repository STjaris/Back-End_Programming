package nl.hu.vkbep.lingo.fileImport.data;

import nl.hu.vkbep.lingo.word.data.WordRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;

public class FileWordReaderTests {

//    WordRepository wordRepository;
//    FileWordWriter fileWordWriter;
//    FileWordReader fileWordReader;
//
//    List<String> wordList = List.of(
//            "pizza",
//            "pizzas",
//            "bier"
//    );

//    @Test
//    @DisplayName("READS FILE IF COUNT == 0")
//    public void startTextImport() throws IOException {
//
//        Long i = 0L;
//
//        when(wordRepository.count()).thenReturn(i);
//
//        when(fileWordWriter.arrayFiltering(wordList)).thenReturn(true);
//
//        boolean result = fileWordReader.startTextImport();
//
//        Assertions.assertTrue(result);
//    }
//
//    @Test
//    @DisplayName("GIVES LIST BACK OF SCANNED LINES")
//    public void fileReader() throws IOException {
//
//        when(fileWordReader.filereader()).then((Answer<?>) wordList);
//
//        List<String> result = fileWordReader.filereader();
//
//        Assertions.assertEquals(wordList, result);
//    }
}

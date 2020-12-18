package nl.hu.vkbep.lingo.fileImport.data;

import nl.hu.vkbep.lingo.fileImport.application.FilterWordsProcessor;
import nl.hu.vkbep.lingo.word.application.WordService;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileWordWriter {

    private FilterWordsProcessor filterWordsProcessor;

    private WordService wordService;

    private List<String> list = new ArrayList<>();


    public FileWordWriter(FilterWordsProcessor filterWordsProcessor, WordService wordService) {
        this.filterWordsProcessor = filterWordsProcessor;
        this.wordService = wordService;
    }

    public boolean arrayFiltering(List<String> lines) {

        //FILTER LENGTH
        for (String line : lines) {
            if (filterWordsProcessor.stringFilteringOnLength(line) && filterWordsProcessor.stringFilteringOnSpecialChar(line)) {
                Word word = new Word();
                word.setWord(line);
                wordService.save(word);
            }
        }
        return true;
    }
}

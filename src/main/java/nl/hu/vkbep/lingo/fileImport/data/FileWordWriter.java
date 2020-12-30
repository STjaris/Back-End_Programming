package nl.hu.vkbep.lingo.fileImport.data;

import nl.hu.vkbep.lingo.fileImport.domain.WordFilter;
import nl.hu.vkbep.lingo.word.application.WordService;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class FileWordWriter {

    private WordFilter wordFilter;
    private WordService wordService;

    public FileWordWriter(WordFilter wordFilter, WordService wordService) {
        this.wordFilter = wordFilter;
        this.wordService = wordService;
    }

    public boolean arrayFiltering(List<String> lines) {

        //FILTER LENGTH
        for (String line : lines) {
            if (wordFilter.stringFiltering(line)) {
                Word word = new Word();
                word.setWord(line);
                wordService.save(word);
            }
        }
        return true;
    }
}

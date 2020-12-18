package nl.hu.vkbep.lingo.fileImport.application;

import org.springframework.stereotype.Service;

@Service
public class FilterWordsProcessor {

    public boolean stringFilteringOnSpecialChar(String input) {
        return input.matches("^[a-z]{5,7}$");
    }

    public boolean stringFilteringOnLength(String input) {

        return input.length() <= 7 && input.length() >= 5;
    }
}

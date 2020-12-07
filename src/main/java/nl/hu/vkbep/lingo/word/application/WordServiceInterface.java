package nl.hu.vkbep.lingo.word.application;

import nl.hu.vkbep.lingo.word.domain.Word;

import java.util.List;

public interface WordServiceInterface {

    void save(Word word);

    boolean wordLengthCheck(String input, int length);

    boolean wordCheck(String input, String word);

    List<String> letterCheck(String input, String word);

    Word getRandomWord();

}

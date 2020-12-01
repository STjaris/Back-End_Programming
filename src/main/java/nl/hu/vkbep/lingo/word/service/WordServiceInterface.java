package nl.hu.vkbep.lingo.word.service;

import nl.hu.vkbep.lingo.word.Word;

import java.util.List;

public interface WordServiceInterface {

    void save(Word word);

    boolean wordLengthCheck(String input, int length);

    boolean wordCheck(String input, String word);

    List<String> letterCheck(String input, String word);

}

package nl.hu.vkbep.lingo.word.application;

import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.word.domain.Word;

import java.util.List;
import java.util.Map;

public interface WordServiceInterface {

    void save(Word word);

    boolean wordLengthCheck(String input, int length);

    boolean wordCheck(String input, String word);

    Map<String, List> letterCheck(String input, String word);

    Word getRandomWord(GameType gameType);

    Word getWordbyId(Long gameid);

    Boolean wordExits(String guess);

}

package nl.hu.vkbep.lingo.word.application;

import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.word.domain.Word;

import java.util.List;
import java.util.Map;

public interface WordServiceInterface {

    Word save(Word word);

    Word getRandomWord(GameType gameType);

    Word getWordbyId(Long gameid);

    boolean attemptChecker(Long wordid,String guess);

    Map wordChecker(String guess, Long wordid);
}

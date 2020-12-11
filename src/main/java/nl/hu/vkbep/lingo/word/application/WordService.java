package nl.hu.vkbep.lingo.word.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.word.data.WordRepository;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WordService implements WordServiceInterface {

    private WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public WordService() {
    }

    @Override
    public void save(Word word) {
        wordRepository.save(word);
    }

    @Override
    public boolean wordLengthCheck(String input, int length) {

        return input.length() == length;
    }

    @Override
    public boolean wordCheck(String input, String word) {

        boolean result = false;

        if (input.equals(word)) {
            result = true;
        } else {
            letterCheck(input, word);
        }

        return result;
    }

    @Override
    public Map<String, List> letterCheck(String input, String word) {

        Map<String, List> map = new HashMap();

        List<String> list = new ArrayList<>();

        int i = 0;
        do {
            String inputIndex = Character.toString(input.charAt(i));
            String wordIndex = Character.toString(word.charAt(i));

            if (inputIndex.equals(wordIndex)) {
                list.add(inputIndex + ": CORRECT");

            } else if (word.contains(inputIndex)) {
                list.add(inputIndex + ": CONTAINS");
            } else
                list.add(inputIndex + ": ABSENT");

            i++;
        }

        while (i < word.length());

        map.put("feedback", list);

        return map;
    }

    @Override
    public Word getRandomWord(GameType gameType) {
        //GET RANDOM WORD FROM DB BY LENGTH
        Word word;

        //LOOP WHILE WORD FROM DB != i;
        do {
            word = wordRepository.getRandomWord();
        }
        while (word.getWord().length() != getLengthByGameType(gameType));

        return word;
    }

    @Override
    public Word getWordbyId(Long wordid) {

        return wordRepository.getById(wordid);

    }

    public int getLengthByGameType(GameType gameType){
        int i;

        //SET INT i ON SAME VALUE OF LETTER LENGTH OF GAME
        if (gameType == GameType.LETTEROF5) {
            i = 5;
        } else if (gameType == GameType.LETTEROF6) {
            i = 6;
        } else {
            i = 7;
        }
        return i;
    }

}

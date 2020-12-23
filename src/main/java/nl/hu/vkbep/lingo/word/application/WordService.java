package nl.hu.vkbep.lingo.word.application;

import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.word.data.WordRepository;
import nl.hu.vkbep.lingo.word.domain.Word;
import nl.hu.vkbep.lingo.word.exception.WordDoesNotExists;
import nl.hu.vkbep.lingo.word.exception.WordLengthNotCorrect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordService implements WordServiceInterface {

    private WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public Word save(Word word) {
        return wordRepository.save(word);
    }

    @Override
    public Map wordChecker(String guess, Long wordid) {

        Map map = new HashMap<>();
        Word word = wordRepository.getById(wordid);

        if (guess.length() == word.getWord().length() && wordExits(guess)) {
            return checkOfLetters(guess, wordid);
        } else if (guess.length() != word.getWord().length() && wordExits(guess)) {
            map.put("NOTE", new WordLengthNotCorrect(guess).getMessage());
        } else{
            map.put("NOTE", new WordDoesNotExists(guess).getMessage());
        }
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

    @Override
    public boolean attemptChecker(Long wordid, String guess) {
        Word word = wordRepository.getById(wordid);

        return word.getWord().equals(guess);
    }

    public Map<String, List<String>> checkOfLetters(String guess, Long wordid) {

        Map<String, List<String>> map = new HashMap<>();
        String word = wordRepository.getById(wordid).getWord();

        List<String> feedback = new ArrayList<>();
        List<String> available = new ArrayList<>();
        Collections.addAll(available, word.split(""));

        for (int i = 0; i < word.length(); i++) {
            String guessChar = Character.toString(guess.charAt(i));
            String wordChar = Character.toString(word.charAt(i));

            if (guessChar.equals(wordChar)) {
                feedback.add("CORRECT");
                available.set(i, null);
            } else {
                feedback.add(null);
            }
        }

        for (int i = 0; i < word.length(); i++) {
            String guessChar = Character.toString(guess.charAt(i));
            if ("CORRECT".equals(feedback.get(i))) {
                continue;
            }

            if (available.contains(guessChar)) {
                feedback.set(i, "CONTAINS");
                available.set(available.indexOf(guessChar), null);
                continue;
            }
            feedback.set(i, "ABSENT");
        }
        map.put("feedback", feedback);

        return map;
    }

    public Boolean wordExits(String guess) {
        return wordRepository.existsByWord(guess);
    }

    public int getLengthByGameType(GameType gameType) {
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

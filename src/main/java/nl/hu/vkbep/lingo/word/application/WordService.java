package nl.hu.vkbep.lingo.word.application;

import nl.hu.vkbep.lingo.word.domain.Word;
import nl.hu.vkbep.lingo.word.data.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class WordService implements WordServiceInterface {

    @Autowired
    private WordRepository wordRepository;

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
    public List<String> letterCheck(String input, String word) {
        String startChar = Character.toString(input.charAt(0));
        String startString = startChar + " " + "- " + "- " + "- " + "- ";
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

        System.out.println(Arrays.toString(list.toArray()));

        return list;
    }

    @Override
    public Word getRandomWord() {
        Word word = new Word();

        do{
            word = wordRepository.getRandomWord();
        }
        while(word.getWord().length() != 5);


        return word;
    }
}

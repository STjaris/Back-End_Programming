package com.example.BackEnd_Programming.word.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WordService implements WordServiceInterface {


    public boolean wordCheck(String input, String word) {

        boolean result = false;

        if (input.equals(word)) {
            result = true;
        } else {
            letterCheck(input, word);
        }

        return result;
    }

    public Map<String, String> letterCheck(String input, String word) {
        Map<String, String> map = new HashMap<>();

        int i = 0;
        do {
            String inputIndex = Character.toString(input.charAt(i));
            String wordIndex = Character.toString(word.charAt(i));

            if(inputIndex.equals(wordIndex)){
                map.put(inputIndex, "CORRECT");
            }else if(word.contains(inputIndex)){
                map.put(inputIndex, "CONTAINS");
            }else
                map.put(inputIndex, "INVALID");

            i++;
        }
        while (i < word.length());

        System.out.println(map.toString());


        return map;
    }


}

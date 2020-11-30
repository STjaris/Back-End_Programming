package com.example.BackEnd_Programming.word.service;

import com.example.BackEnd_Programming.word.Word;

import java.util.List;

public interface WordServiceInterface {

    void save(Word word);

    Boolean wordLengthCheck(String input, int length);

    boolean wordCheck(String input, String word);

    List<String> letterCheck(String input, String word);

}

package com.example.BackEnd_Programming.word.service;

import java.util.List;
import java.util.Map;

public interface WordServiceInterface {

    Boolean wordLengthCheck(String input, int length);

    boolean wordCheck(String input, String word);

    List<String> letterCheck(String input, String word);

}

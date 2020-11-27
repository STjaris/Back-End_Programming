package com.example.BackEnd_Programming.word.service;

import java.util.Map;

public interface WordServiceInterface {

    boolean wordCheck(String input, String word);

    Map<String, String> letterCheck(String input, String word);

}

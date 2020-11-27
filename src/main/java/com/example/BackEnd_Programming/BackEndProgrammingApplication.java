package com.example.BackEnd_Programming;

import com.example.BackEnd_Programming.fileImport.textImportInterface;
import com.example.BackEnd_Programming.fileImport.textimport;
import com.example.BackEnd_Programming.word.service.WordService;
import com.example.BackEnd_Programming.word.service.WordServiceInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class BackEndProgrammingApplication {

    private static final textImportInterface textImportInterface = new textimport();

    private static final WordServiceInterface wordServiceInterface = new WordService();

    public static void main(String[] args) {
        SpringApplication.run(BackEndProgrammingApplication.class, args);
        startTextImport();

        startNewRound();
    }

    public static void startTextImport() {
        try {
            textImportInterface.arrayFiltering(textImportInterface.filereader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startNewRound() {

        int i = 0;

        Scanner scanner = new Scanner(System.in);
        String word = textImportInterface.getRandomWord(5);

        System.out.println(word);

        System.out.println("5letter ronde:");
        System.out.println("- " + "- " + "- " + "- " + "- ");

        System.out.println("Raadbeurt:");
        String input = scanner.next();

        if (wordServiceInterface.wordCheck(input, word)) {
            System.out.println("CORRECT");
        }
        else{
            System.out.println("FALSE");

        }

//        do{
//
//
//            i++;
//        }
//        while(i <= 5);




    }


}

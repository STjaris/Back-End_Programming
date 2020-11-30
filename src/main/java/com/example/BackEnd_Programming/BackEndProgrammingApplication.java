package com.example.BackEnd_Programming;

import com.example.BackEnd_Programming.fileImport.TextImportInterface;
import com.example.BackEnd_Programming.fileImport.Textimport;
import com.example.BackEnd_Programming.word.service.WordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackEndProgrammingApplication {

    @Autowired
    private static final TextImportInterface textImportInterface = new Textimport();
    //private static final RoundServiceInterface roundServiceInterface = new RoundService();
    @Autowired
    private static WordServiceInterface wordServiceInterface;

    public static void main(String[] args) {
        SpringApplication.run(BackEndProgrammingApplication.class, args);
        textImportInterface.startTextImport();
        //startNewRound();
    }

//    public static void startNewRound() {
//
//        int i = 0;
//
//        Scanner scanner = new Scanner(System.in);
//        String word = textImportInterface.getRandomWord(5);
//
//        String inputIndex = Character.toString(word.charAt(0));
//
//        System.out.println(word);
//
//        System.out.println("5letter ronde:");
//        System.out.println(inputIndex + " " + "- " + "- " + "- " + "- ");
//
//
//        //INPUTCHECK AND ROUNDCOUNT
//        do {
//            System.out.println("Raadbeurt " + i + " :");
//            String input = scanner.next();
//
//            if (wordServiceInterface.wordLengthCheck(input, 5)) {
//
//                if (wordServiceInterface.wordCheck(input, word)) {
//                    System.out.println("CORRECT");
//                    break;
//                }
//
//            } else {
//                System.out.println("INPUT NOT VALID, WORD LENGTH NOT CORRECT");
//            }
//
//
//            i++;
//        }
//        while (i < 5);
//
//        System.out.println("GAME HAS ENDED");
//
//
//    }


}

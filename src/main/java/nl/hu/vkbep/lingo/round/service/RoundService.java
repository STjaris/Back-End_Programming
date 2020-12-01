package nl.hu.vkbep.lingo.round.service;

import nl.hu.vkbep.lingo.word.repository.WordRepository;
import nl.hu.vkbep.lingo.word.service.WordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Scanner;

@Service
public class RoundService implements RoundServiceInterface {

    private WordRepository wordRepository;
    private WordServiceInterface wordServiceInterface;

    @Autowired
    public RoundService(WordRepository wordRepository, WordServiceInterface wordServiceInterface) {
        this.wordRepository = wordRepository;
        this.wordServiceInterface = wordServiceInterface;
    }

    public RoundService() {
    }

    @Override
    public void startNewRound() {

        int i = 0;

        Scanner scanner = new Scanner(System.in);
        String word;

        //GET WORD WITH LENGTH ...
        do{
            int rnd = new Random().nextInt();
            word = wordRepository.getById((long) rnd).toString();

        }
        while(word.length() != 5);

        String inputIndex = Character.toString(word.charAt(0));

        System.out.println(word);

        System.out.println("5letter ronde:");
        System.out.println(inputIndex + " " + "- " + "- " + "- " + "- ");


        //INPUTCHECK AND ROUNDCOUNT
        do {
            System.out.println("Raadbeurt " + i + " :");
            String input = scanner.next();

            if (wordServiceInterface.wordLengthCheck(input, 5)) {

                if (wordServiceInterface.wordCheck(input, word)) {
                    System.out.println("CORRECT");
                    break;
                }

            } else {
                System.out.println("INPUT NOT VALID, WORD LENGTH NOT CORRECT");
            }


            i++;
        }
        while (i < 5);

        System.out.println("GAME HAS ENDED");


    }


}

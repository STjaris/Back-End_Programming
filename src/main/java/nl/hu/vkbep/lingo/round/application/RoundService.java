package nl.hu.vkbep.lingo.round.application;

import nl.hu.vkbep.lingo.round.data.RoundRepository;
import nl.hu.vkbep.lingo.round.domain.Round;
import nl.hu.vkbep.lingo.word.data.WordRepository;
import nl.hu.vkbep.lingo.word.application.WordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Scanner;

@Service
public class RoundService implements RoundServiceInterface {

    private WordRepository wordRepository;
    private WordServiceInterface wordServiceInterface;
    private RoundRepository roundRepository;

    @Autowired
    public RoundService(WordRepository wordRepository, WordServiceInterface wordServiceInterface, RoundRepository roundRepository) {
        this.wordRepository = wordRepository;
        this.wordServiceInterface = wordServiceInterface;
        this.roundRepository = roundRepository;
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

    @Override
    public Round createNewRound() {
        Round round = new Round();
        roundRepository.save(round);

        return round;
    }


}

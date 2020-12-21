package nl.hu.vkbep.lingo.round.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.round.data.RoundRepository;
import nl.hu.vkbep.lingo.round.domain.Round;
import nl.hu.vkbep.lingo.round.domain.RoundStatus;
import nl.hu.vkbep.lingo.round.domain.RoundType;
import nl.hu.vkbep.lingo.word.application.WordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoundService implements RoundServiceInterface {

    private WordServiceInterface wordService;
    private RoundRepository roundRepository;


    @Autowired
    public RoundService(WordServiceInterface wordService, RoundRepository roundRepository) {
        this.wordService = wordService;
        this.roundRepository = roundRepository;
    }

    @Override
    public Map playRound(Game game, Long wordid, String guess) {

        //CREATE ROUND
        Round round = new Round(RoundStatus.NOTCORRECT, roundCheckFromGame(game), game, guess);

        roundRepository.save(round);

        Map<String, String> map = new HashMap<>();

        //GET WORD FROM GAME
        String word = wordService.getWordbyId(wordid).getWord();

        //CHECK IF WORD EXISTS
        if (wordService.wordExits(guess)) {

            return checkWord(word, guess, round);

        } else {
            map.put("note", "INPUT NOT VALID, WORD DOES NOT EXISTS");
        }
        return map;
    }


    public Map checkWord(String word, String guess, Round round) {
        if (wordService.wordLengthCheck(guess, roundTypeCheck(round))) {
            return provideFeedback(word, guess, round);
        }
        return Map.of("note", "INPUT NOT VALID, WORD LENGTH NOT CORRECT");

    }


    private Map provideFeedback(String word, String guess, Round round) {

        if (wordService.wordCheck(guess, word)) {
            round.setRoundStatus(RoundStatus.CORRECT);
            roundRepository.save(round);
            return Map.of("note", "CORRECT");
        } else {
            return wordService.letterCheck(guess, word);
        }
    }

    @Override
    public int countRoundPerGame(Game game) {
        return roundRepository.countAllByGame(game);
    }

    public RoundType roundCheckFromGame(Game game) {
        //CHECKS GAMETYPE AND RETURNS CORRESPONDING ROUNDTYPE
        if (game.getGameType().equals(GameType.LETTEROF5)) {
            return (RoundType.LETTEROF5);
        } else if (game.getGameType().equals(GameType.LETTEROF6)) {
            return (RoundType.LETTEROF6);
        } else {
            return (RoundType.LETTEROF7);
        }
    }

    public int roundTypeCheck(Round round) {
        //RETURNS INT CORRESPONDING ROUNDTYPE
        if (round.getRoundType() == RoundType.LETTEROF5) {
            return 5;
        } else if (round.getRoundType() == RoundType.LETTEROF6) {
            return 6;
        } else {
            return 7;
        }
    }

}

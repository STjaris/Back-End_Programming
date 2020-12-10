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

    private WordServiceInterface wordServiceInterface;
    private RoundRepository roundRepository;

    @Autowired
    public RoundService(WordServiceInterface wordServiceInterface, RoundRepository roundRepository) {
        this.wordServiceInterface = wordServiceInterface;
        this.roundRepository = roundRepository;
    }

    public RoundService() {
    }

    @Override
    public Round createNewRound() {
        Round round = new Round();
        roundRepository.save(round);

        return round;
    }

    @Override
    public Map playRound(Game game, Long wordid, String guess) {

        //CREATE ROUND
        Round round = new Round(RoundStatus.NOTCORRECT, roundCheckFromGame(game), game, guess);

        //SAVE ROUND
        roundRepository.save(round);

        Map<String, String> map = new HashMap<>();

        //GET WORD FROM GAME
        String word = wordServiceInterface.getWordbyId(wordid).getWord();

        //GUESS CHECK ON LENGTH
        if (wordServiceInterface.wordLengthCheck(guess, roundTypeCheck(round))) {
            if (wordServiceInterface.wordCheck(guess, word)) {
                map.put("note", "CORRECT");
            } else {
                return wordServiceInterface.letterCheck(guess, word);
            }
        } else {
            map.put("note", "INPUT NOT VALID, WORD LENGTH NOT CORRECT");
        }

        return map;
    }

    @Override
    public int countRoundPerGame(Game game) {

        return roundRepository.countAllByGame(game);
    }

    public RoundType roundCheckFromGame(Game game) {
        //CHECKS GAMETYPE AND RETURNS CORRESPONDING ROUNDTYPE
        if (game.getGameType() == GameType.LETTEROF5) {
            return (RoundType.LETTEROF5);
        } else if (game.getGameType() == GameType.LETTEROF6) {
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

package nl.hu.vkbep.lingo.round.application;

import nl.hu.vkbep.lingo.game.domain.Game;
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

        //CREATE AND SAVE ROUND
        Round round = new Round(RoundStatus.ONGOING, RoundType.LETTEROF5, game, guess);
        roundRepository.save(round);

        Map<String, String> map = new HashMap<>();

        //GET WORD FROM GAME
        String word = wordServiceInterface.getWordbyId(wordid).getWord();

        //GUESS CHECK ON LENGTH
        if (wordServiceInterface.wordLengthCheck(guess, roundTypeCheck(round))) {
            if (wordServiceInterface.wordCheck(guess, word)) {
                map.put("feedback", "CORRECT");
            } else {
                return wordServiceInterface.letterCheck(guess, word);
            }
        } else {
            map.put("feedback", "INPUT NOT VALID, WORD LENGTH NOT CORRECT");
        }

        return map;
    }

    @Override
    public int countRoundPerGame(Game game) {

        return roundRepository.countAllByGame(game);
    }

    public int roundTypeCheck(Round round) {
        if (round.getRoundType() == RoundType.LETTEROF5) {
            return 5;
        } else if (round.getRoundType() == RoundType.LETTEROF6) {
            return 6;
        } else {
            return 7;
        }
    }

}

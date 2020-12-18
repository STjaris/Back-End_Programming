package nl.hu.vkbep.lingo.score.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.player.data.PlayerRepository;
import nl.hu.vkbep.lingo.score.data.ScoreRepository;
import nl.hu.vkbep.lingo.score.domain.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService implements ScoreServiceInterface{

    ScoreRepository scoreRepository;
    PlayerRepository playerRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository, PlayerRepository playerRepository) {
        this.scoreRepository = scoreRepository;
        this.playerRepository = playerRepository;
    }

    public List<Score> getHighscore() {
        return scoreRepository.getAllOrderByScore();
    }

    public Score calculateScores(Game game, int multiplier, Long playerid , List<Long> list) {

        System.out.println("LIST: " + list.toString());

        double totalscore = 0;

        for (Object ignored : list) {
            int roundCount = game.getRoundCount();
            totalscore += calculation(roundCount, multiplier);
        }

        Score score = new Score(totalscore, playerRepository.getPlayerById(playerid));
        scoreRepository.save(score);

        return score;
    }

    public double calculation(int roundcount, int multiplier) {
        final int maxRoundCount = 5;
        return ((maxRoundCount/(double)roundcount) * multiplier);
    }

}

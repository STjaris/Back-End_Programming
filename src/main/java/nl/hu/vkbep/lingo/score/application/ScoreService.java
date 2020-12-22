package nl.hu.vkbep.lingo.score.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.player.data.PlayerRepository;
import nl.hu.vkbep.lingo.score.data.ScoreRepository;
import nl.hu.vkbep.lingo.score.domain.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

@Service
public class ScoreService implements ScoreServiceInterface{

    ScoreRepository scoreRepository;
    PlayerRepository playerRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository, PlayerRepository playerRepository) {
        this.scoreRepository = scoreRepository;
        this.playerRepository = playerRepository;
    }

    public List<Map> getHighscore() {

        List<Score> list = scoreRepository.getAllOrderByScore();
        List<Map> resultList = new ArrayList<>();
        int i = 1;

        for(Score score : list){
            Map map = new HashMap<>();
            map.put("id", i);
            map.put("score", score.getScore());
            map.put("player", score.getPlayer().getName());
            resultList.add(map);
            i++;
        }
        return resultList;
    }

    public Score calculateScores(Game game, int multiplier, Long playerid , List<Long> list) {
        Score score;

        double totalscore = 0;

        for (Object ignored : list) {
            int roundCount = game.getRoundCount();
            totalscore += calculation(roundCount, multiplier);
        }

        if (findScoreByGameid(list.get(0))) {
            score = getScoreByGameid(list.get(0));

            if (score.getScore() > 0) {
                totalscore += score.getScore();
            }

            score.setScore(totalscore);

        } else {
            score = new Score(totalscore, playerRepository.getPlayerById(playerid), list.get(0));
        }

        scoreRepository.save(score);

        return score;
    }

    public double calculation(int roundcount, int multiplier) {
        final int maxRoundCount = 5;
        return ((maxRoundCount / (double) (roundcount + 1)) * multiplier);
    }

    public Score getScoreByGameid(Long gameid) {
        return scoreRepository.getScoreByGameid(gameid);
    }

    public boolean findScoreByGameid(Long gameid) {
        return scoreRepository.existsScoreByGameidEquals(gameid);
    }

}

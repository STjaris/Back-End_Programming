package nl.hu.vkbep.lingo.score.application;

import nl.hu.vkbep.lingo.player.data.PlayerRepository;
import nl.hu.vkbep.lingo.score.data.ScoreRepository;
import nl.hu.vkbep.lingo.score.domain.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public double calculation(int roundcount, int multiplier) {
        final int maxRoundCount = 5;
        return ((maxRoundCount / (double) (roundcount + 1)) * multiplier);
    }
}

package nl.hu.vkbep.lingo.score.application;

import nl.hu.vkbep.lingo.player.data.PlayerRepository;
import nl.hu.vkbep.lingo.score.data.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService implements ScoreServiceInterface{

    ScoreRepository scoreRepository;
    PlayerRepository playerRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository, PlayerRepository playerRepository) {
        this.scoreRepository = scoreRepository;
        this.playerRepository = playerRepository;
    }

    public double calculation(int roundcount, int multiplier) {
        final int maxRoundCount = 5;
        return ((maxRoundCount / (double) (roundcount + 1)) * multiplier);
    }
}

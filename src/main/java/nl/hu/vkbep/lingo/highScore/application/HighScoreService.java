package nl.hu.vkbep.lingo.highScore.application;

import nl.hu.vkbep.lingo.highScore.data.HighScoreRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HighScoreService implements HighScoreServiceInterface{

    private HighScoreRepository highScoreRepository;

    public HighScoreService(HighScoreRepository highScoreRepository) {
        this.highScoreRepository = highScoreRepository;
    }

    @Override
    public Map getHighscore() {
        return highScoreRepository.getAllByScoreOrderByScoreScoreAsc();
    }
}

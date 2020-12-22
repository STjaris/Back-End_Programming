package nl.hu.vkbep.lingo.score.presentation;

import nl.hu.vkbep.lingo.score.application.ScoreService;
import nl.hu.vkbep.lingo.score.domain.Score;
import nl.hu.vkbep.lingo.score.exception.HighscoreNotFound;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ScoreController {

    ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/highscore")
    public List<Map> getHighscore() throws HighscoreNotFound{

            return scoreService.getHighscore();
    }
}

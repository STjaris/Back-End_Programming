package nl.hu.vkbep.lingo.highScore.presentation;

import nl.hu.vkbep.lingo.highScore.application.HighScoreServiceInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HighScoreController {

    HighScoreServiceInterface highScoreServiceInterface;

    public HighScoreController(HighScoreServiceInterface highScoreServiceInterface) {
        this.highScoreServiceInterface = highScoreServiceInterface;
    }

    @GetMapping("/highscore")
    public Map getHighscore() {
        return highScoreServiceInterface.getHighscore();
    }
}

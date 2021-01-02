package nl.hu.vkbep.lingo.gameSession.presentation;

import nl.hu.vkbep.lingo.gameSession.application.GameSessionService;
import nl.hu.vkbep.lingo.gameSession.presentation.exception.HighscoreNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GameSessionController {

    private GameSessionService gameSessionService;

    @Autowired
    public GameSessionController(GameSessionService gameSessionService) {
        this.gameSessionService = gameSessionService;
    }

    @GetMapping("/highscore")
    public List<Map> getHighscore() throws HighscoreNotFound {

        return gameSessionService.getHighscore();
    }
}

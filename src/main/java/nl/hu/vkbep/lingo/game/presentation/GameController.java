package nl.hu.vkbep.lingo.game.presentation;

import nl.hu.vkbep.lingo.game.application.GameServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GameController {

    private Long playerid = 1L;

    private GameServiceInterface gameServiceInterface;

    public GameController(GameServiceInterface gameServiceInterface) {
        this.gameServiceInterface = gameServiceInterface;
    }

    @GetMapping("/games")
    public Map startGame() {
        return gameServiceInterface.createNewGame();
    }

    @PostMapping("/games/{gameid}/attempt")
    public Map makeAttempt(@PathVariable Long gameid, @RequestBody String attempt) {

        return gameServiceInterface.guess(gameid, attempt);
    }
}

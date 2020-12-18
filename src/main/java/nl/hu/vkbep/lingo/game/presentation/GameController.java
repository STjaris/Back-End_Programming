package nl.hu.vkbep.lingo.game.presentation;

import nl.hu.vkbep.lingo.game.application.GameServiceInterface;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GameController {

    private Map<String, Long> gameIdMap = new HashMap();

    private GameServiceInterface gameServiceInterface;

    public GameController(GameServiceInterface gameServiceInterface) {
        gameIdMap.clear();
        this.gameServiceInterface = gameServiceInterface;
    }

    @PostMapping("/games")
    public Map startGame() {
        return gameServiceInterface.createNewGame();
    }

    @PostMapping("/games/{gameid}/attempt")
    public Map makeAttempt(@PathVariable Long gameid, @RequestBody String attempt) {
        gameIdMap.put("id", gameid);
        return gameServiceInterface.guess(gameid, attempt);
    }

    public void getPayedGameId() {

    }
}

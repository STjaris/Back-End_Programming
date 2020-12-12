package nl.hu.vkbep.lingo.game.presentation;

import nl.hu.vkbep.lingo.game.application.GameServiceInterface;
import nl.hu.vkbep.lingo.game.domain.Game;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GameController {

    private GameServiceInterface gameServiceInterface;

    public GameController(GameServiceInterface gameServiceInterface) {
        this.gameServiceInterface = gameServiceInterface;
    }


    @PostMapping("/games")
    public Map startGame() {
        return gameServiceInterface.createNewGame();
    }

    @PostMapping("/games/{gameid}/attempt")
    public Map makeAttempt(@PathVariable Long gameid, @RequestBody String attempt) {

        return gameServiceInterface.guess(gameid, attempt);
    }

//    @RequestMapping("/hello")
//    public String start(){
//        return "HELLO";
//    }
}

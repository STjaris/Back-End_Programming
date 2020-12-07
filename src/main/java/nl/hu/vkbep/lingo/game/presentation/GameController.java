package nl.hu.vkbep.lingo.game.presentation;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.application.GameServiceInterface;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private GameServiceInterface gameServiceInterface;

    @PostMapping("/games")
    public Game startGame(){
        return gameServiceInterface.createNewGame();
    }
}

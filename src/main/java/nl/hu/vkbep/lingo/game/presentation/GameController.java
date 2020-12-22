package nl.hu.vkbep.lingo.game.presentation;

import nl.hu.vkbep.lingo.game.application.GameServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GameController {

    private Long playerid = 1L;
    List<Long> listofGameid = new ArrayList<>();

    private GameServiceInterface gameServiceInterface;

    public GameController(GameServiceInterface gameServiceInterface) {
        this.gameServiceInterface = gameServiceInterface;
    }

    @GetMapping("/games")
    public Map startGame() {
        //if(listofGameid.size() >= 1){
            listofGameid.clear();
        //}
        return gameServiceInterface.createNewGame();
    }

    @PostMapping("/games/{gameid}/attempt")
    public Map makeAttempt(@PathVariable Long gameid, @RequestBody String attempt) {

        if(!listofGameid.contains(gameid)){
            listofGameid.add(gameid);
        }

        return gameServiceInterface.guess(gameid, attempt, listofGameid, playerid);
    }
}

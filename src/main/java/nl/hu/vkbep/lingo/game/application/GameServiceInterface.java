package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.domain.Game;

import java.util.List;
import java.util.Map;

public interface GameServiceInterface {

    Map createNewGame();

    Game getById(Long gameid);

    Map guess(Long gameid, String guess, List<Long> listOfGameid, Long playerid);
}

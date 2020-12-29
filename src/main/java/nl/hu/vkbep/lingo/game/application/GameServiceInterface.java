package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.domain.Game;

import java.util.Map;

public interface GameServiceInterface {

    Map createNewGame(Long playerid);

    Game getById(Long gameid);

    Map makeAttempt(Long gameid, String guess);
}

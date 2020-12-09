package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.domain.Game;

import java.util.Map;

public interface GameServiceInterface {

    void getAllGames();

    Game createNewGame();

    Game getById(Long gameid);

    Map guess(Long gameid, String guess);
}
package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.domain.Game;

public interface GameServiceInterface {

    void getAllGames();

    Game createNewGame();
}

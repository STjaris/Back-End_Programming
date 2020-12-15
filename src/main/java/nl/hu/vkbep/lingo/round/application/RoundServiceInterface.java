package nl.hu.vkbep.lingo.round.application;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.round.domain.Round;

import java.util.Map;

public interface RoundServiceInterface {

    Map playRound(Game game, Long wordid, String guess);

    int countRoundPerGame(Game game);
}

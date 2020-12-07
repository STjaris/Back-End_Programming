package nl.hu.vkbep.lingo.round.application;

import nl.hu.vkbep.lingo.round.domain.Round;

import java.util.Map;

public interface RoundServiceInterface {

    void startNewRound();

    Round createNewRound();

    Map playRound(Long gameid, String guess);
}

package nl.hu.vkbep.lingo.round.application;

import nl.hu.vkbep.lingo.round.domain.Round;

public interface RoundServiceInterface {

    void startNewRound();

    Round createNewRound();
}

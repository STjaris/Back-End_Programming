package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.data.GameRepository;
import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.round.application.RoundServiceInterface;
import nl.hu.vkbep.lingo.round.data.RoundRepository;
import nl.hu.vkbep.lingo.round.domain.Round;
import nl.hu.vkbep.lingo.round.domain.RoundStatus;
import nl.hu.vkbep.lingo.round.domain.RoundType;
import nl.hu.vkbep.lingo.word.application.WordServiceInterface;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@DisplayName("GameServiceTests")
public class GameServiceTests {

    @Autowired
    private RoundRepository roundRepository;

    private final Game game = new Game();
    private final String guess = "TESTS";

    private final Round round1 = new Round(RoundStatus.NOTCORRECT, RoundType.LETTEROF5, game, guess);
    private final Round round2 = new Round(RoundStatus.NOTCORRECT, RoundType.LETTEROF5, game, guess);

    public GameServiceTests() {
    }

    @Test
    public void createNewGame() {

        WordServiceInterface wordMock = Mockito.mock(WordServiceInterface.class);
        GameRepository gameMock = Mockito.mock(GameRepository.class);
        RoundServiceInterface roundMock = Mockito.mock(RoundServiceInterface.class);

        GameService service = new GameService(gameMock, wordMock, roundMock);
        service.createNewGame();

        Mockito.verify(gameMock, Mockito.times(1));
        Mockito.verify(wordMock, Mockito.times(1));
    }

    @Test
    public void countRoundsPerGame() {
        RoundRepository roundDaoMock = Mockito.mock(RoundRepository.class);

        roundRepository.save(round1);
        roundRepository.save(round2);

        List<Round> rounds = roundDaoMock.findAll();

        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round1));
        assertTrue(rounds.contains(round2));


    }

    @Test
    public void getGameById() {

        this.roundRepository.save(round1);

        Optional<Round> result = this.roundRepository.findById(round1.getId());
        result.ifPresentOrElse(
                round -> Assertions.assertEquals(round1, round),
                () -> fail("ROUND MUST BE PRESENT")
        );
    }


}

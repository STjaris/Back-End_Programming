package nl.hu.vkbep.lingo.game.application;

import nl.hu.vkbep.lingo.game.data.GameRepository;
import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.round.application.RoundService;
import nl.hu.vkbep.lingo.round.data.RoundRepository;
import nl.hu.vkbep.lingo.round.domain.Round;
import nl.hu.vkbep.lingo.round.domain.RoundStatus;
import nl.hu.vkbep.lingo.round.domain.RoundType;
import nl.hu.vkbep.lingo.word.application.WordService;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("GameServiceTests")
public class GameServiceTests {

    private final String guess = "TESTS";
    private final Word word = new Word(1L, "tests");
    private final Game game = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word);
    private final Round round1 = new Round(RoundStatus.NOTCORRECT, RoundType.LETTEROF5, game, guess);
    private final Round round2 = new Round(RoundStatus.NOTCORRECT, RoundType.LETTEROF5, game, guess);

    WordService wordMock;
    GameRepository gameDaoMock;
    RoundService roundMock;
    GameService gameServiceMock;
    RoundRepository roundRepositoryMock;

    @MockBean
    private MockMvc mockMvc;

    public GameServiceTests() {
    }

    @Before
    public void init() {


        wordMock = Mockito.mock(WordService.class);
        gameDaoMock = Mockito.mock(GameRepository.class);
        roundMock = Mockito.mock(RoundService.class);
        gameServiceMock = Mockito.mock(GameService.class);

        roundRepositoryMock = Mockito.mock(RoundRepository.class);

    }


//    @Test
//    public void createNewGame() throws Exception {
//
//        //Mockito.verify(gameSpy, times(1)).createNewGame();
//
//        //Mockito.verify(roundSpy, times(1)).wordLengthCheck(guess,word, round1);
//        //Mockito.verify(wordSpy, times(1)).createNewGame();
//
////        gameServiceMock.createNewGame();
////
////        Mockito.verify(gameMock, times(1));
////        Mockito.verify(wordMock, times(1));
//
//
//        //when(gameServiceMock.createNewGame()).thenReturn((Map) game);
//
////        this.mockMvc.perform(post("/games"))
////                .andExpect(status().isOk())
////                .andExpect(content().string("gameid"));
//
//
//    }

//    @ParameterizedTest
//    public void countRoundsPerGame(boolean expectedResult) {
//        roundRepositoryMock.save(round1);
//        roundRepositoryMock.save(round2);
//
//        List<Round> rounds = roundRepositoryMock.findAll();
//
//        assertEquals(2, rounds.size());
//        assertTrue(rounds.contains(round1));
//        assertTrue(rounds.contains(round2));
////
////
////
////
//        boolean result = gameServiceMock.checkRoundCountPerGame(game, word.getId(), guess)
//                .containsKey("gamestatus");
//
//        assertEquals(result,expectedResult);
//    }

//    @Test
//    public void getGameById() {
//        roundRepositoryMock.save(round1);
//
//        Optional<Round> result = roundRepositoryMock.findById(round1.getId());
//        result.ifPresentOrElse(
//                round -> Assertions.assertEquals(round1, round),
//                () -> fail("ROUND MUST BE PRESENT")
//        );
//    }


}

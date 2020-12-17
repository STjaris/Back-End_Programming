package nl.hu.vkbep.lingo.game.presentation;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.game.domain.GameStatus;
import nl.hu.vkbep.lingo.game.domain.GameType;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTests {

//    @MockBean
//    GameController gameController;
//    @Autowired
//    private MockMvc mockMvc;

//    @Test
//    @DisplayName("CREATE NEW GAME")
//    void createsNewGame() throws Exception {
//        Word word = new Word(1L, "tests");
//
//        Map feedback = Map.of("gameid", 1L, "feedback", word.toString());
//
//        when(gameController.startGame()).thenReturn(feedback);
//
//        mockMvc.perform(post("/games"))
//                .andExpect(status().isOk())
//                .andExpect(content().json(feedback.toString()));
//    }

//    @Test
//    @DisplayName("CREATE NEW GAME")
//    void makeAttempt() throws Exception {
//        Word word = new Word(1L, "tests");
//        Game game = new Game(GameStatus.NOTSTARTED, GameType.LETTEROF5, word, 0, 0);
//
//        Map feedback = Map.of("gameid", 1L, "feedback", word.toString());
//
//        when(gameController.startGame()).thenReturn(feedback);
//
//        mockMvc.perform(post("/games/{gameid}/attempt"))
//                .andExpect(status().isOk())
//                .param("gameid", anyLong())
//                .andExpect(content().contentType(feedback));
//    }
}

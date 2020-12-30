package nl.hu.vkbep.lingo.gameSession.data;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.gameSession.domain.GameSession;
import nl.hu.vkbep.lingo.score.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {


    GameSession getGameSessionByGamesIsContaining(Game game);

    @Query(
            value = "SELECT * FROM GAMESESSION ORDER BY TOTAL_SCORE DESC", nativeQuery = true
    )
    List<GameSession> getAllOrderByScore();

}

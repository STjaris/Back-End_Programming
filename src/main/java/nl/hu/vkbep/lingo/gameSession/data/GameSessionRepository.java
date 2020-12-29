package nl.hu.vkbep.lingo.gameSession.data;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.gameSession.domain.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {


    GameSession getGameSessionByGamesIsContaining(Game game);

}

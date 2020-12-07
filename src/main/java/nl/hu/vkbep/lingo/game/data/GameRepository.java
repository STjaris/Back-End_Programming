package nl.hu.vkbep.lingo.game.data;

import nl.hu.vkbep.lingo.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game getById(Long gameid);

}

package nl.hu.vkbep.lingo.game.repository;

import nl.hu.vkbep.lingo.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}

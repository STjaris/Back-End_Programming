package nl.hu.vkbep.lingo.player.data;

import nl.hu.vkbep.lingo.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player getUserByName(String name);

    Player getPlayerById(Long playerid);
}

package nl.hu.vkbep.lingo.round.data;

import nl.hu.vkbep.lingo.round.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, Long> {
}

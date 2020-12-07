package nl.hu.vkbep.lingo.score.data;

import nl.hu.vkbep.lingo.score.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}

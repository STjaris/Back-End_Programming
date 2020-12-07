package nl.hu.vkbep.lingo.highScore.data;

import nl.hu.vkbep.lingo.highScore.domain.HighScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighScoreRepository extends JpaRepository<HighScore, Long> {
}

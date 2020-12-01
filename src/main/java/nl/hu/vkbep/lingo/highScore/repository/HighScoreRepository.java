package nl.hu.vkbep.lingo.highScore.repository;

import nl.hu.vkbep.lingo.highScore.HighScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighScoreRepository extends JpaRepository<HighScore, Long> {
}

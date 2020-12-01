package nl.hu.vkbep.lingo.score.repository;

import nl.hu.vkbep.lingo.score.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}

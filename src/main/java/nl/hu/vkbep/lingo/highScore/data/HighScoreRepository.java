package nl.hu.vkbep.lingo.highScore.data;

import nl.hu.vkbep.lingo.highScore.domain.HighScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface HighScoreRepository extends JpaRepository<HighScore, Long> {

    Map getAllByScoreOrderByScoreScoreAsc();
}

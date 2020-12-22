package nl.hu.vkbep.lingo.score.data;

import nl.hu.vkbep.lingo.score.domain.Score;
import nl.hu.vkbep.lingo.word.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {

    @Query(
            value = "SELECT * FROM SCORE ORDER BY SCORE DESC", nativeQuery = true
    )
    List<Score> getAllOrderByScore();

    Score getScoreByGameid(Long gameid);

    boolean existsScoreByGameidEquals(Long gameid);


}

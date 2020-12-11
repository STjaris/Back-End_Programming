package nl.hu.vkbep.lingo.highScore.domain;

import nl.hu.vkbep.lingo.score.domain.Score;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class HighScore {

    @Id
    private Long id;


    @ManyToOne
    @JoinColumn(name = "scoreid", nullable = false)
    private Score score;

    public HighScore(Long id, Score score) {
        this.id = id;
        this.score = score;
    }

    public HighScore() {

    }
}

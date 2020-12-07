package nl.hu.vkbep.lingo.score.domain;

import nl.hu.vkbep.lingo.player.domain.Player;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private double score;

    @ManyToOne
    @JoinColumn(name = "playerid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player player;


    public Score(Long id, double score, Player player) {
        this.id = id;
        this.score = score;
        this.player = player;
    }

    public Score() {
    }

    public Long getId() {
        return id;
    }
    public double getScore() {
        return score;
    }
    public Player getPlayer() {
        return player;
    }
}

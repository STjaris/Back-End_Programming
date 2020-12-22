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


    private double score;

    @ManyToOne
    @JoinColumn(name = "playerid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player player;

    @NotNull
    private Long gameid;


    public Score(double score, Player player, Long gameid) {
        this.score = score;
        this.player = player;
        this.gameid = gameid;
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

    public void setScore(double score) {
        this.score = score;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setGameid(Long gameid) {
        this.gameid = gameid;
    }
}

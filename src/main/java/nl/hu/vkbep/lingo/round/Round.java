package nl.hu.vkbep.lingo.round;

import nl.hu.vkbep.lingo.game.Game;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "round")
public class Round {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gameid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Game game;

    @NotNull
    private RoundStatus roundStatus;
    @NotNull
    private RoundType roundType;

    public Round(Long id, Game game, RoundStatus roundStatus, RoundType roundType) {
        this.id = id;
        this.game = game;
        this.roundStatus = roundStatus;
        this.roundType = roundType;
    }

    public Round() {
    }

    public Long getId() {
        return id;
    }
    public Game getGame() {
        return game;
    }
    public RoundStatus getRoundStatus() {
        return roundStatus;
    }
    public RoundType getRoundType() {
        return roundType;
    }
}

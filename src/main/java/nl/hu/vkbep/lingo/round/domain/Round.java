package nl.hu.vkbep.lingo.round.domain;

import nl.hu.vkbep.lingo.game.domain.Game;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private RoundStatus roundStatus;
    @NotNull
    private RoundType roundType;

    @OneToOne
    @JoinColumn(name = "gameid", nullable = false)
    private Game game;

    public Round(RoundStatus roundStatus, RoundType roundType, Game game) {
        this.roundStatus = roundStatus;
        this.roundType = roundType;
        this.game = game;
    }

    public Round() {
    }

    public Long getId() {
        return id;
    }
    public RoundStatus getRoundStatus() {
        return roundStatus;
    }
    public RoundType getRoundType() {
        return roundType;
    }
    public Game getGame() {
        return game;
    }
}

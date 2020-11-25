package com.example.BackEnd_Programming.game;

import com.example.BackEnd_Programming.round.Round;
import com.example.BackEnd_Programming.score.Score;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private GameStatus gameStatus;

    @NotNull
    private GameType gameType;

    @ManyToOne
    @JoinColumn(name = "roundid", nullable = false)
    private Round round;

    @OneToOne
    @JoinColumn(name = "scoreid", nullable = false)
    private Score score;

    public Game(Long id, Round round, Score score) {
        this.id = id;
        this.round = round;
        this.score = score;
    }

    public Game() {

    }

    public Long getId() {
        return id;
    }
    public GameStatus getGameStatus() {
        return gameStatus;
    }
    public GameType getGameType() {
        return gameType;
    }
    public Round getRound() {
        return round;
    }
    public Score getScore() {
        return score;
    }
}
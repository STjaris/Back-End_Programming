package nl.hu.vkbep.lingo.gameSession.domain;

import nl.hu.vkbep.lingo.game.domain.Game;
import nl.hu.vkbep.lingo.player.domain.Player;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "gamesession")
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @OneToMany
    private List<Game> games;

    @OneToOne
    private Player player;

    private double totalScore;


    public GameSession(List<Game> games, Player player, double totalScore) {
        this.games = games;
        this.player = player;
        this.totalScore = totalScore;
    }

    public GameSession() {
    }

    public Long getId() {
        return id;
    }
    public List<Game> getGames() {
        return games;
    }
    public Player getPlayer() {
        return player;
    }
    public double getTotalScore() {
        return totalScore;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }
}

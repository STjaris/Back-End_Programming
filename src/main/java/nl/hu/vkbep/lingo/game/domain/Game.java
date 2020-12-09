package nl.hu.vkbep.lingo.game.domain;

import com.sun.istack.NotNull;
import nl.hu.vkbep.lingo.round.domain.Round;
import nl.hu.vkbep.lingo.score.domain.Score;
import nl.hu.vkbep.lingo.word.domain.Word;

import javax.persistence.*;

@Entity
@Table(name = "game")
public class Game {

    private final int maxDuratie = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private GameStatus gameStatus;
    @NotNull
    private GameType gameType;

//    @ManyToOne
//    @JoinColumn(name = "roundid", nullable = false)
//    private Round round;

//    @OneToOne
//    @JoinColumn(name = "scoreid", nullable = false)
//    private Score score;
    @ManyToOne
    @JoinColumn(name = "wordid", nullable = false)
    private Word word;
    private int duratie;


    public Game(GameStatus gameStatus, GameType gameType, Word word, Round round, Score score, int duratie) {
        this.gameStatus = gameStatus;
        this.gameType = gameType;
        this.word = word;
        //this.round = round;
        //this.score = score;
        this.duratie = duratie;
    }

    public Game() {

    }

    public Long getId() {
        return id;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    //    public Round getRound() {
//        return round;
//    }
//    public Score getScore() {
//        return score;
//    }
    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public int getMaxDuratie() {
        return maxDuratie;
    }

    public int getDuratie() {
        return duratie;
    }

    //    public void setRound(Round round) {
//        this.round = round;
//    }
//    public void setScore(Score score) {
//        this.score = score;
//    }
    public void setDuratie(int duratie) {
        this.duratie = duratie;
    }

}
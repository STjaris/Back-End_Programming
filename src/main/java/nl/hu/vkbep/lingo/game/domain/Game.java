package nl.hu.vkbep.lingo.game.domain;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
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

    @ManyToOne
    @JoinColumn(name = "wordid", nullable = false)
    private Word word;

    private int duratie;

    @Nullable
    private int roundCount;

    private double score;


    public Game(GameStatus gameStatus, GameType gameType, Word word, int duratie, int roundCount, double score) {
        this.gameStatus = gameStatus;
        this.gameType = gameType;
        this.word = word;
        this.duratie = duratie;
        this.roundCount = roundCount;
        this.score = score;
    }

    public Game(Long id, GameStatus gameStatus, GameType gameType, Word word) {
        this.id = id;
        this.gameStatus = gameStatus;
        this.gameType = gameType;
        this.word = word;
    }

    public Game(GameStatus gameStatus, GameType gameType, Word word) {
        this.gameStatus = gameStatus;
        this.gameType = gameType;
        this.word = word;
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

    public void setDuratie(int duratie) {
        this.duratie = duratie;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameStatus=" + gameStatus +
                ", gameType=" + gameType +
                ", word=" + word +
                ", duratie=" + duratie +
                ", roundCount=" + roundCount +
                '}';
    }

    public double calculation(int roundcount, int multiplier) {
        final int maxRoundCount = 5;
        return ((maxRoundCount / (double) (roundcount + 1)) * multiplier);
    }
}

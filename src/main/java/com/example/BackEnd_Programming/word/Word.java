package com.example.BackEnd_Programming.word;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name= "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String word;

    public Word(long id, String word) {
        this.id = id;
        this.word = word;
    }

    public Word() {
    }

    public long getId() {
        return id;
    }
    public String getWord() {
        return word;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "Word{" + "word='" + word + '\'' +
                '}';
    }
}

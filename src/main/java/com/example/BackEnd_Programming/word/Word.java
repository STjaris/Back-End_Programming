package com.example.BackEnd_Programming.word;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "word")
public class Word {

    @Id
    private long id;

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
}

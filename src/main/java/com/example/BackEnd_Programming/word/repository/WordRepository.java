package com.example.BackEnd_Programming.word.repository;

import com.example.BackEnd_Programming.word.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {

    Word getById(Long wordid);

    Word save(Word word);
}

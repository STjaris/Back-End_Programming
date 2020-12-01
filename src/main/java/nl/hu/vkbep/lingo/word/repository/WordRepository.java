package nl.hu.vkbep.lingo.word.repository;

import nl.hu.vkbep.lingo.word.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {

    Word getById(Long wordid);

    Word save(Word word);

    int countAllByIdGreaterThan(Long id);


}

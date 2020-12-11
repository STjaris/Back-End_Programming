package nl.hu.vkbep.lingo.word.data;

import nl.hu.vkbep.lingo.word.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordRepository extends JpaRepository<Word, Long> {

    Word getById(Long wordid);

    Word save(Word word);

    int countAllByIdGreaterThan(Long id);

    @Query(
            value = "SELECT * FROM WORD ORDER BY RANDOM() LIMIT 1", nativeQuery = true
    )
    Word getRandomWord();

}

package nl.hu.vkbep.lingo.word.presentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class WordLengthNotCorrect extends RuntimeException {
    public WordLengthNotCorrect(@PathVariable String guess) {
        super("THE LENGTH OF THE WORD: "+  guess  +" IS NOT CORRECT");
    }
}

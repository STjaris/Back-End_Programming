package nl.hu.vkbep.lingo.word.presentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class WordDoesNotExists extends RuntimeException{
    public WordDoesNotExists(@PathVariable String guess) {
        super("THE GIVEN WORD: " + guess + " DOES NOT EXISTS");
    }
}

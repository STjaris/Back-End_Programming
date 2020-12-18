package nl.hu.vkbep.lingo.score.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HighscoreNotFound extends RuntimeException{

    public HighscoreNotFound(){
        super("NO SCORES FOUND");
    }
}

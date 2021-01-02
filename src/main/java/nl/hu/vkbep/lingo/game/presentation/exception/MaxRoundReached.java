package nl.hu.vkbep.lingo.game.presentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class MaxRoundReached extends RuntimeException{
    public MaxRoundReached(@PathVariable Long gameid){
        super("MAX ROUND HAS BEEN REACHED FOR GAME: " + gameid);
    }
}

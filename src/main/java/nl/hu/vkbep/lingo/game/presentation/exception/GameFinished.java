package nl.hu.vkbep.lingo.game.presentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class GameFinished extends RuntimeException{
    public GameFinished() {
    super("CONGRATZ, THE GAME HAS ENDED");
    }
}

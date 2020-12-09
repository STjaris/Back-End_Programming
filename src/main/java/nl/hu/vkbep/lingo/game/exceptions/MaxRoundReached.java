package nl.hu.vkbep.lingo.game.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class MaxRoundReached extends RuntimeException {
    public MaxRoundReached() {
        super("Max round reached, game has ended.");
    }
}

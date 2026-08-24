package dev.daniel.movies.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Data already exists")
public class MovieExceptionConflict extends MovieException {

    public MovieExceptionConflict(String message) {
        super(message);
    }

    public MovieExceptionConflict(String message, Throwable cause) {
        super(message, cause);
    }

}

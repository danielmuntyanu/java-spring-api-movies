package dev.daniel.globals;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.daniel.movies.exceptions.MovieException;
import dev.daniel.movies.exceptions.MovieExceptionConflict;
import dev.daniel.movies.exceptions.MovieExceptionNotFound;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieExceptionNotFound.class)
    public ResponseEntity<String> handleMovieNotFoundException(MovieExceptionNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(MovieExceptionConflict.class)
    public ResponseEntity<String> handleMovieConflictException(MovieExceptionConflict exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(MovieException.class)
    public ResponseEntity<String> handleMovieException(MovieException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

}

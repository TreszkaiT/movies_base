package hu.nye.webapp.movies.exception;

import java.util.List;

/**
 * a ContorllerAdvice Validation hibáit kapja el a Controller-en keresztül
 */
public class InvalidMovieException extends RuntimeException {

    private List<String> message;

    public InvalidMovieException(String message, List<String> messages) {           // egy hibaüzenetet és egy messages listát adunk meg, hogy mi volt a hiba
        super(message);
        this.message = messages;
    }

    // hogy a movieContorllerAdvice-ben lekérhessem az exception.getmessages()-t
    @Override
    public List<String> getMessage() {
        return message;
    }
}

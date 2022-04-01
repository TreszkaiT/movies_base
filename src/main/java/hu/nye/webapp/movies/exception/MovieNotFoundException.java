package hu.nye.webapp.movies.exception;

/**
 * Saját hibaüzenet
 *
 *
 *
 */
public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String message) {
        super(message);
    }
}

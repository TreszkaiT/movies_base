package hu.nye.webapp.movies.response;

import java.util.List;

/**
 * a validálálnál a hibákat fogja kiírni MovieControllerből ContollerAdvice
 */
public class ErrorResponse {

    private List<String> message;

    public ErrorResponse(List<String> message) {
        this.message = message;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}

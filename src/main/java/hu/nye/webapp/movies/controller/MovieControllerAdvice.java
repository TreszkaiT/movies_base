package hu.nye.webapp.movies.controller;

import hu.nye.webapp.movies.exception.MovieNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Ha valami Contorller-ből kirepül egy Exception, akkor ez el tudja kapni, és lekezeli
 */

// Bean lesz így belőle, a Spring meg másrészt tudni fogja, hogy ő azért felelős, hogy Controlleres Exceptionokat kapjon el
@RestControllerAdvice(assignableTypes = MovieController.class)          // (assignableTypes = MovieController.class) :: e nélkül az összes Controller fájlért ő felel
public class MovieControllerAdvice {

    @ExceptionHandler(value = MovieNotFoundException.class)                                                 // Ebből tudja, hogy Exceptiont kell kezelnie;;  ő csak ezért felelős : MovieNotFoundException.class
    public ResponseEntity<Void> handleMovieNotFoundException(){
        return ResponseEntity.badRequest().build();                                                         // 404-es HTTP status code;   build() : üres response body-val
    }
}

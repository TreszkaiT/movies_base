package hu.nye.webapp.movies.controller;

import hu.nye.webapp.movies.entity.Movie;
import hu.nye.webapp.movies.repository.MovieRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ezek a metódusok fogják lekezelni a kéréseket az egyes endpointokra pl. GET/movies  a https://editor.swagger.io/ -oldalon GET/POST/....
 */

// ez mondja meg azt, hogy a Spring foglalkozzon ezzel az osztállyal, és ez egy kontroller osztály lesz
    // Az annotáció még azt is mondja, hogy ez egy spéci Rest típusú kontroller
    // és egy Bean-t fog csinálni ebből az osztályból -> így a Spring tudja kezelni az életciklusukat a Bean-nek, tehát nekünk nem kell példányosítani azt az osztályt, hanem
    // a Spring tudni fogja, hogy ha beindítja az alkalmazást, akkor látja hogy itt egy RestController Annotáció, így csinál belőle egy példányt ebből az osztályból, és
    // ezután már akár tudunk is hozzá kéréseket intézni
@RestController
public class MovieController {

    // itt field-ként vesszük fel, és generálunk egyből hozzá egy konstruktort... ez a dependency injection megvalósítása... constructor injection
    // ekkor ha elindítjuk az alkalmazást, akkor a Spring látja majd, hogy van nekünk egy RestControllerünk, így ebből csinálni kell egy Bean-t,
    // és azt is látja, hogy van neki egy konstruktora, ami vár egy MovieRepository -t , és a Spring az magától oda tudja adni ezt a függőséget
    // ennek az objektumnak, mikor példányosítja.

    // a MovieControllernek a RestController Annotáció által lesz egy Bean-je
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;

        Movie movie1 = new Movie(); // hogy legyen példaadat is
        movie1.setTitle("Star Wars");
        Movie movie2 = new Movie();
        movie2.setTitle("Terminator");

        movieRepository.save(movie1);   // lementi az adatbázisba
        movieRepository.save(movie2);
    }

    // ez kezeli le az összes filmet
    // movies list a visszatérési értéke: azaz valamilyen kollekció  List<String> = filmcímek egyenlőre
    @RequestMapping(path = "/movies", method = RequestMethod.GET)      // RequestMapping: megmondjuk, hogy ez a metódus a GET/movies  hívásra alkalmas;;;; azaz ez egy kérés Mappalése, ha bejön egy kérés, akkor azt le tudjuk mappelni erre a metódusra  CTRL+P metódusainak kilistázása
    public List<Movie> findAll(){

        /*
        // ez csak egy statikus lista, de adatbázissal lenne jó dolgozni, így a Spring Data-t vesszük elő
        public List<String> findAll(){
        return List.of(
                "Star Wars",
                "Terminator"
        );*/

        //MovieRepository movieRepository;
        //movieRepository.

        return movieRepository.findAll();
    }
}

package hu.nye.webapp.movies.controller;

import hu.nye.webapp.movies.dto.MovieDTO;
import hu.nye.webapp.movies.entity.Movie;
import hu.nye.webapp.movies.exception.InvalidMovieException;
import hu.nye.webapp.movies.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * I. itt kezdetben az adatbázis téma volt, de az nem ide kell igazából, így kicseréltük egy Service-re (MovieService)
 * és a Service ad egy metódust findAll(), mely visszaadja az összes filmet. Ezt tudjuk használni a Controllerben
 * -- MovieService.java itt folytajuk
 *
 * Entity-k és adatbázis művelet itt nem lehet!!! meg egy controller-ben sem
 *
 * ezek a metódusok fogják lekezelni a kéréseket az egyes endpointokra pl. GET/movies  a https://editor.swagger.io/ -oldalon GET/POST/....
 */

// ez mondja meg azt, hogy a Spring foglalkozzon ezzel az osztállyal, és ez egy kontroller osztály lesz
    // Az annotáció még azt is mondja, hogy ez egy spéci Rest típusú kontroller
    // és egy Bean-t fog csinálni ebből az osztályból -> így a Spring tudja kezelni az életciklusukat a Bean-nek, tehát nekünk nem kell példányosítani azt az osztályt, hanem
    // a Spring tudni fogja, hogy ha beindítja az alkalmazást, akkor látja hogy itt egy RestController Annotáció, így csinál belőle egy példányt ebből az osztályból, és
    // ezután már akár tudunk is hozzá kéréseket intézni
@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);        // az osztályhoz elkérek egy loggert,

    // a MovieDTO bevezetésével ez az osztály már nem a MovieRepository-n fog függni, hanem a MovieService-en; az implementációt meg majd a Spring magától intézi, nekünk nem kell
    // nekünk már csak a sor végén ALt+Enter, és generáltatunk egy konstruktort is hozzá automatikusan
    private final MovieService movieService;    // e felé nem kell @Autowire annotáció, mert Spring x. verzió felett már nem szükséges, ha az osztály felett ott az annotáció kitéve (RestContorller)

            // ide, vagy egy sorral fentebb nem kell az @Autowired Annotáció, mert Spring x. verzió felett már megnézi az osztály Annotációját @RestController, és tudja, hogy annak a feladata behzúni ezeket, és ez a konstruktor egyértelmű, és egyszerű
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // ez pedig már a movieDTO-t fog visszaadni, és a movieService-t fog használni
    //@RequestMapping(path = "/movies", method = RequestMethod.GET)                            // RequestMapping: megmondjuk, hogy ez a metódus a GET/movies  hívásra alkalmas;;;; azaz ez egy kérés Mappalése, ha bejön egy kérés, akkor azt le tudjuk mappelni erre a metódusra  CTRL+P metódusainak kilistázása
    @RequestMapping(method = RequestMethod.GET)                                             // path = "/movies",  ezt felrakva az osztály tetejére, ide már nem is kell, mert automatikusan megöröklik majd
    public ResponseEntity<List<MovieDTO>> findAll(){
        return ResponseEntity.ok(movieService.findAll());
    }

    // adatokat ír az adatbázisba
    // (@RequestBody MovieDTO movieDTO): ha bejön egy kérés, és volt RequestBody-ja, akkor átkonvertálja azt MovieDTO objektummá, és ezzel tudnunk dolgozni ezen metódus törzsében
    //@RequestMapping(path = "/movies", method = RequestMethod.POST)
    @RequestMapping(method = RequestMethod.POST)                                             // path = "/movies",  ezt felrakva az osztály tetejére, ide már nem is kell, mert automatikusan megöröklik majd  ;;; ha ide írnék be egy path = "/get", -et akkor összeadódna, így a címe /movies/get lenne
    public ResponseEntity<MovieDTO> create(@RequestBody @Valid MovieDTO movieDTO, BindingResult bindingResult){      // ResponseEntity : a HTTP válaszon tudunk módosítani vele. 200,201... úgy hogy a MovieDTO-t becsomagoljuk ebbe a ResponseEntity generikus osztályba; HTTP headereket is bele tudunk még e mellett pakolni   ;;;  @Valid a MovieDTO-ban használja így a validációt  ;;  , BindingResult bindingResult  a validációs hibákat ebbe teszi bele, és mi azokat le tudjuk innen kérni
        checkErrors(bindingResult);                                                             // alul alapmetódust írunk a validációs hibák lekezelésére

        MovieDTO savedMovie = movieService.create(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED)                                        // beállítom a 201-es stásusz kódot: HttpStatus.CREATED
            .body(savedMovie);                                                                  // HTTP body beállítása
        //return movieService.create(movieDTO);                                                 // itt meg meghívom az Implementáció create metódusát
    }

    // id alapján kérjük le a filmeket
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)                              // /{id}  kapott id
    public ResponseEntity<MovieDTO> findById(@PathVariable(name = "id") Long identifier){       // @PathVariable Long id ezzel a kapott /{id}-t tudjuk kezelni ezen metóduson belül
        Optional<MovieDTO> optionalMovieDTO = movieService.findById(identifier);

        ResponseEntity<MovieDTO> response;
        if (optionalMovieDTO.isPresent()) {                                                     // ha van benne bármilyen érték, azaz megtalálltuk a filmet
            response = ResponseEntity.ok(optionalMovieDTO.get());                               // akkor kiszedem az értékét
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);                  // amúgy meg nem találtam meg
        }
        return null;
    }

    // frissítés id alapján
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<MovieDTO> update(@RequestBody @Valid MovieDTO movieDTO, BindingResult bindingResult){                     // @RequestBody MovieDTO movieDTO:  a Request Body-ban várja az infót
        checkErrors(bindingResult);                                                             // alul alapmetódust írunk a validációs hibák lekezelésére

        MovieDTO updatedMovie = movieService.update(movieDTO);
        return ResponseEntity.ok(updatedMovie);                                                 // 200-as hiba
    }

    // id alapján törlés
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){                                  // <Void>  : üres response Entity-t ad vissza, így ezen tudjuk állítgatni a HTTP status kódot
        movieService.delete(id);
        return ResponseEntity.noContent().build();                                              // 204-es HTTP status code
    }

    // validáció hibáinak kiíratásához egy segésmetódus felülre POST-ba
    private void checkErrors(BindingResult bindingResult){
        LOGGER.info("bindingResult has errors = {}", bindingResult.hasErrors());                // a stringet kiírja {}-ebbe meg behettesíti ezt bindingResult.hasErrors() hogy van-e hiba
        LOGGER.info("errors = {}", bindingResult.getAllErrors());                               // kiírjuk az összes hibát, amit csak talál   a hibaválaszt a response package-be tesszük bele
        // itt dobunk egy Exceptiont, mely a controllerAdvice-ben lévő hibát fogja meg,, ehhez kell egy Invalid

        if(bindingResult.hasErrors()){
            List<String> messages = new ArrayList<>();                                          // listát hozok létre

            for(FieldError fieldError : bindingResult.getFieldError()){
                messages.add(fieldError.getField() + " - " + fieldError.getDefaultMessage());  // majd kiírja, melyik field, és feltöltöm a hibákkal
            }

            throw new InvalidMovieException("Invalid movie", messages);                         // ha hibát látok, akkor dobok egy Exceptiont, ami a hiba okát is tartalmazza
        }
    }
}

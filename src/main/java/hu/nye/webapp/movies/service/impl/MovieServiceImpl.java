package hu.nye.webapp.movies.service.impl;

import hu.nye.webapp.movies.dto.MovieDTO;
import hu.nye.webapp.movies.entity.Movie;
import hu.nye.webapp.movies.repository.MovieRepository;
import hu.nye.webapp.movies.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Ő a konkrét implementációja a MovieService.java interface-nak
 * Ő tudja hivogatni a MovieRepository-t, és felszedi az adatbázisból a kellő dolgokat, majd átalakítja findAll() metódus Steam-jével megfelelő formátumba, és visszaadja a Controller felé
 * hogy legyen ebből is egy Bean, ráraktuk a Service Annotációt
 *
 *  -- UtilConfiguration.java itt folytajuk a modelMapper külső osztály miatt, mert külső osztályra nem lehet Annotációt tenni, csak configuration osztállyal
 *
 * a MovieContorller-t hogy tudjam hasnálni kell hozzá egy implementáció
 *
 * azt szeretnénk, hogy az implementáció a MovieRepository-val gyűjtse össze az adatokat az adatbázisból -> így megodunk neki egy ilyen függőséget a MovieRepository interface-re
 * majd generáltatok neki egy konstruktort, a sor végén ALt+Enter
 *
 * és azrét, hogy a Spring be tudja neki adni a függőséget, és a függőséget át tudja majd adni a Controllerünknek, ahhoz ebből az osztályból egy Bean-t kell csinálni egy Annotációval egyszerűen
 */

// és azrét, hogy a Spring be tudja neki adni a MovieRepository függőséget, és a függőséget át tudja majd adni a Controllerünknek, ahhoz ebből az osztályból egy Bean-t kell csinálni egy Annotációval egyszerűen
@Service
public class MovieServiceImpl implements MovieService {

    // azt szeretnénk, hogy az implementáció a MovieRepository-val gyűjtse össze az adatokat az adatbázisból; -> így megodunk neki egy ilyen függőséget a MovieRepository interface-re
    // majd generáltatok neki egy konstruktort, a sor végén ALt+Enter
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;  // mivel ezt kívülről kaptuk, a dependency-ből, így valahol léteznie kellene belőle egy Bean-nek, hogy a Spring le tudja példányosítani ezt az osztályt
                                            // ehhez a modelMapper oszályhoz nem tudunk odamenni, és beleírni az Annotációt, mert ezt kívűlről kaptuk, hanem van egy másik lehetőség is, hogy Bean-eket definiáljunk
                                            // például java config által -> így ehhez egy configuration packaget kell csinálni

        // ha futtatni akarjuk, akkor hogy az adatbázisnak legyen valami tartalma, így ezt ide kell másolni:
        Movie movie1 = new Movie(); // hogy legyen példaadat is
        movie1.setTitle("Star Wars");
        Movie movie2 = new Movie();
        movie2.setTitle("Terminator");

        movieRepository.save(movie1);   // lementi az adatbázisba
        movieRepository.save(movie2);


    }

    // és ki kell egészítenünk az implementációt itt alul
    @Override
    public List<MovieDTO> findAll() {
        List<Movie> movieList = movieRepository.findAll();      // az összes filmet felszedjük a movieRepositoryval, ami az adatbázisban van, és elmentük ezt egy Listába
                                                                // ezt közvetlenül még nem tudom visszaadni, mert ő egy Movie típusú lista, és nekem egy MovieDTO kell
        // hogy ne kelljen átliterálni a movieList-en, és mindegyik esetében csinálik egy új movieDTO-t és át set-telgetem a Movie-ba, ezért húzta be a dependency-be a modelmapper függőséget, mely automatikusan tud konvertálni egyik osztályból a másikba
        // most nálunk könnyű ez, mert mindkét osztályban ugyanazok a fieldek (id, title, ....)  ;;; de bonyolultabb esetekben célszerű saját logikát írni rá ; így fent fel kell venni függőségként, majd Alt+Enter-el felveszem a konstruktorba ezt is
        // majd

        // vagy végigmegyek for ciklussal a List-án, vagy inkább egyszerűbben csináljuk Stream-el, így veszem a java streamAPI-ját, és a Movie-kat beleteszem a stream-be, és az ősszes elemre végrehajtok egy map-pelést; azaz átlakítom mássá az elemeket
        return movieList.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))   // movie adatot MovieDTO.class osztályba akarom alakítani
                .collect(Collectors.toList());                          // és ezek után ezzel a művelettel begyűjtöm egy listába. Majd gyakorlatilag ezeket vissza is tudom már így adni a return-t eléírva

        //return null;
    }

    // tehát mégegyszer:
    // a Service lehív az adatbázishoz
    // a movieRepository által megkaptuk az összes filmet, ami benne van
    // utána a Steam-el a listát átalakítom movieDTO típusúvá, és ezt fogom visszaadni
    // így megszületett a findAll() metódus implementációja
}

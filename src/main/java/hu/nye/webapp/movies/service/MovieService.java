package hu.nye.webapp.movies.service;

import hu.nye.webapp.movies.dto.MovieDTO;

import java.util.List;

/**
 *
 * service réteg: itt van az üzeleti logika
 * szerepe: az entity és repository rétegeket tudja hívni; azaz az adatbázis rétegeit, dolgait fel tudja használni
 *
 * Interfacekre próbálunk programozni, hogy az itt definiált műveletekre az implementációt bármikor ki tudjuk cserélni
 */
public interface MovieService {

    // tudnia kell ennek az osztálynak begyűjtenie, h. milyen filmek vannak az adatbázisban
    // ez a metódus Movie-kat tud visszaadni
    // <Movie> -t nem írhatok ide, mert akkor Entity-k lennének továbbítva a MovieController.java-nak, és nagyon hozzákötném ezt az interfacet a Movie.java adathoz, ami nem jó,
    // így létre kell hozni egy dto-t. Data Transfer Object
    List<MovieDTO> findAll();
}

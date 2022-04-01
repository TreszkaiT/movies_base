package hu.nye.webapp.movies.service;

import hu.nye.webapp.movies.dto.MovieDTO;

import java.util.List;

/**
 * Ez csak egy Interface, ami jó dolog,
 * mert az implementációt mögötte bármikor ki tudjuk cserélni,
 * mert csinálhatunk tetszőleges számú implementációt
 *
 * Ennek van egy konkrét implementációja (MovieServiceImpl.java)
 * -- MovieServiceImpl.java itt folytajuk
 *
 *
 * service réteg: itt van az üzeleti logika
 * szerepe: az entity és repository rétegeket tudja hívni; azaz az adatbázis rétegeit, dolgait fel tudja használni
 *
 * Interfacekre próbálunk programozni, hogy az itt definiált műveletekre az implementációt bármikor ki tudjuk cserélni
 */
public interface MovieService {

    // Ez a metódus egy MovieDTO típusú Listát ad vissza
    // tudnia kell ennek az osztálynak begyűjtenie, h. milyen filmek vannak az adatbázisban
    // ez a metódus Movie-kat tud visszaadni
    // <Movie> -t nem írhatok ide, mert akkor Entity-k lennének továbbítva a MovieController.java-nak, és nagyon hozzákötném ezt az interfacet a Movie.java adathoz, ami nem jó,
    // így létre kell hozni egy dto-t. Data Transfer Object
    List<MovieDTO> findAll();
}

package hu.nye.webapp.movies.repository;

import hu.nye.webapp.movies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ez fogja az adatbázis műveleteket elvégezni
 *
 * ez egy JPA-s repository lesz
 *
 * extends JpaRepository : ezt utána kell írni, ezt a Spring Boot- adja.
 * És ezt tudjuk kiterjeszteni. Ez egy generikus interface.
 * paraméterei: <T,ID> = <Movie,Long>
 *  T : a Movie repository milyen tipusu objektumok, Entity-k felett operál
 *  ID : milyen típusú az ID
 */

// az alapműveletekhez csak ennyi kell, semmi más. Csak a MovieController.java-ban be kell írni, hogy MovieRepository movieRepository;  és movieRepository.  után nagyon sok műveletet kapunk

// ez csak egy interface, de mikor beindítjuk a Spring Boot-sot, akkor az ennek az interfacenak az összes műveletét legenerálja,
    // és ahol ezt az interface-t használjuk, oda be fog szúrni nekünk egy implementációt, és azon keresztül tudjuk az adatbázist Query-zgetni
    // Így egy sor SQL-t se kellett megírnunk, hanem ennyi.
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // itt azért saját metódusokat is tudunk definiálni, ha szeretnénk nem csak beépítetteket... a Spring Data JPA dokumentációjában utána tudunk olvasni
    // List<User> findByEmailAddressAndLastName(String emailAddress, String lastName);          // And miatt a metódus névből a Spring ki tudja generálni akár az SQL Query-t is!!! docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
}

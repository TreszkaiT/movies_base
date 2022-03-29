package hu.nye.webapp.movies.entity;

import javax.persistence.Entity;
import java.util.Date;
import java.util.Set;

/**
 * hogy tudjuk használni a Spring Data JPA-t és adatbázisba dolgokat tudjunk menteni, kell készíteni egy Entity-t
 *
 * a https://editor.swagger.io/   Schemas részében láthatóak az objektumok, pl a Movie; és az is látható, ha kinyitja az ember, hogy milyen paraméterei vannak azaz ide milyen változókat kell felvenni: id, title, tagline...
 */

// így lesz belőle Entity amúgy csak sima osztály lenne
    // így már a JPA ezt az osztályt már le tudja majd képezni java objektumból adatbázis beli sorrá
    // és a Spring Boot Data JPA-nak azt fogja megmondani, hogy itt egy Entity-vel ellátott osztál, így automatikusan meg fog csinálni egy adatbázisbeli táblát; így ezzel se kell foglalkozni, mert a Spring Boot ezt megoldja
@Entity
public class Movie {
    private Long id;
    private String title;
    private String tagline;
    private double voteAverage;
    private int voteCount;
    private Date releaseDate;
    private String posterPath;
    private String overview;
    private int budget;
    private int revenue;
    private Set<String> genres;
    private Integer runtime;
}

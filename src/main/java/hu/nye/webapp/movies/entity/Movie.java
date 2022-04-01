package hu.nye.webapp.movies.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * ebből az osztályból -> adatbázis beli sor és tábla készítése automatikusan
 *
 * ezzel hozzuk létre az adatbázist, és a benne lévő táblákat, fejléceket
 * hogy tudjuk használni a Spring Data JPA-t és adatbázisba dolgokat tudjunk menteni, kell készíteni egy Entity-t
 *
 * a https://editor.swagger.io/   Schemas részében láthatóak az objektumok, pl a Movie; és az is látható, ha kinyitja az ember, hogy milyen paraméterei vannak azaz ide milyen változókat kell felvenni: id, title, tagline...
 */

// így lesz belőle Entity amúgy csak sima osztály lenne
    // így már a JPA ezt az osztályt már le tudja majd képezni java objektumból adatbázis beli sorrá
    // és a Spring Boot Data JPA-nak azt fogja megmondani, hogy itt egy Entity-vel ellátott osztál, így automatikusan meg fog csinálni egy adatbázisbeli táblát; így ezzel se kell foglalkozni, mert a Spring Boot ezt megoldja
@Entity
public class Movie {

    // hogy az adatbázisba egyedi legyen az id, kevés csak úgy felvenni, Annotáció kell hozzá. Azaz az elsődleges kulcsot meg kell mondani itt, hogy mi legyen  @Id a javax.persistence csomagból kell
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)             // azt mondja meg, hogy hogyan generálódjon egy ilyen Entitás Id-ja. Megmondjuk, hogy milyen stratégiát használjon ehhez. Auto=AutoIncrement lesz
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
    // magától a Set Stringek halmazát nem tudja betenni az adatbázisba, sőt Exceptionnal el is száll az alkalmazás
    // így ezt az Annotációt rá kell tenni. Azaz csinál hozzá egy külön kis táblát, és abból lesznek hozzácsatolva az egyes műfajok a Movies tábla adott filmjéhez
    @ElementCollection
    private Set<String> genres;
    private Integer runtime;

    // kell még hozzá egy üres konstruktor ;; mindenképp meg kell adni ;; és gettereket és settereket -> Code menü / Generate / Constructor / Select None
    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }
}

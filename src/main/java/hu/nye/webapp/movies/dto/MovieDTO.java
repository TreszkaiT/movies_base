package hu.nye.webapp.movies.dto;

import javax.persistence.ElementCollection;
import java.util.Date;
import java.util.Set;

/**
 * Data Transfer Object = csak adattovábbításra használatos osztály ez
 * ugyanazt csinálja, mint a Movie, csak nem Entity lesz, így nem lesz szükségünk a Movie.java-ban az Entity-s és JPA-s Annotációkra
 *
 * ezt az osztályt használjuk fel a MovieService-ban
 *
 * Mivel ez nem Entity, így már nincs szükésünk itt az Annotációkra
 */
public class MovieDTO {

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

    public MovieDTO() {
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

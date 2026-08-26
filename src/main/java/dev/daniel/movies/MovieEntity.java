package dev.daniel.movies;

import java.util.List;

import dev.daniel.actors.ActorEntity;
import dev.daniel.genres.GenreEntity;
import dev.daniel.years.ReleaseYearEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class MovieEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "release_year", nullable = false)
    private ReleaseYearEntity releaseYear;

    @ManyToMany
    @JoinTable(
        name = "movies_genres",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<GenreEntity> genres;

    @ManyToMany
    @JoinTable(
        name = "movies_actors",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<ActorEntity> actors;

    public MovieEntity() {
    }

    public MovieEntity(Long id, String title, ReleaseYearEntity releaseYear) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ReleaseYearEntity getReleaseYear() {
        return releaseYear;
    }
    

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(ReleaseYearEntity releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }

    public List<ActorEntity> getActors() {
        return actors;
    }

    public void setActors(List<ActorEntity> actors) {
        this.actors = actors;
    }

}

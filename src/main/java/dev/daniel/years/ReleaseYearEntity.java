package dev.daniel.years;

import java.util.List;

import dev.daniel.movies.MovieEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "years")
public class ReleaseYearEntity {
    @Id
    @Column(name = "id", nullable = false, length = 4)
    private Long id;

    @OneToMany(mappedBy = "releaseYear")
    private List<MovieEntity> movies;

    public ReleaseYearEntity() {
    }

    public ReleaseYearEntity(Long release_year) {
        this.id = release_year;
    }

    public Long getId() {
        return id;
    }

    public List<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }

    
    
}

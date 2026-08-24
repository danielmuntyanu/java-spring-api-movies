package dev.daniel.movies;

import dev.daniel.years.ReleaseYearEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class MovieEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "release_year", nullable = false)
    private ReleaseYearEntity releaseYear;

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

}

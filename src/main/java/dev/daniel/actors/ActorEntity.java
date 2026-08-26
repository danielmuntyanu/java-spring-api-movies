package dev.daniel.actors;

import java.util.List;

import dev.daniel.movies.MovieEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "actors")
public class ActorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = true, length = 20)
    private String lastName;

    @ManyToMany
    @JoinTable(
        name = "movies_actors",
        joinColumns = @JoinColumn(name = "actor_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<MovieEntity> movies;

    public ActorEntity() {
    }

    public ActorEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }

}

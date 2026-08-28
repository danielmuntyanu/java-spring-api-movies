package dev.daniel.genres;

import org.junit.jupiter.api.Test;

import dev.daniel.actors.ActorEntity;
import dev.daniel.movies.MovieEntity;
import dev.daniel.years.ReleaseYearEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.List;

public class GenreEntityTest {

    @Test
    void testGenreEntity_Initialization() {

        GenreEntity genre = new GenreEntity("Action");

        ReleaseYearEntity year = new ReleaseYearEntity(2002L);
        MovieEntity movie = new MovieEntity(1L, "Spider-Man", year);
        movie.setGenres(List.of(
            genre,
            new GenreEntity("Adventure")
        ));
        movie.setActors(List.of(
            new ActorEntity("Tobey", "Maguire"),
            new ActorEntity("Kirsten", "Dunst")
        ));

        genre.setMovies(List.of(movie));

        assertThat(genre, is(instanceOf(GenreEntity.class)));
        assertThat(genre.getClass().getDeclaredFields().length, is(equalTo(3)));

    }

    @Test
    void testGenreEntity() {

        GenreEntity genre = new GenreEntity(1L, "Action"); 

        ReleaseYearEntity year = new ReleaseYearEntity(2002L);
        MovieEntity movie = new MovieEntity(1L, "Spider-Man", year);
        movie.setGenres(List.of(
            genre,
            new GenreEntity("Adventure")
        ));
        movie.setActors(List.of(
            new ActorEntity("Tobey", "Maguire"),
            new ActorEntity("Kirsten", "Dunst")
        ));

        List<MovieEntity> movies = List.of(movie);
        genre.setMovies(movies);


        assertThat(genre.getId(), is(equalTo(1L)));
        assertThat(genre.getName(), is(equalTo("Action")));
        assertThat(genre.getMovies(), is(equalTo(movies)));

    }

}

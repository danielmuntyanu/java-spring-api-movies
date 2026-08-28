package dev.daniel.years;

import org.junit.jupiter.api.Test;

import dev.daniel.actors.ActorEntity;
import dev.daniel.genres.GenreEntity;
import dev.daniel.movies.MovieEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.List;

public class ReleaseYearEntityTest {

    @Test
    void testReleaseYearEntity_Initialization() {

        ReleaseYearEntity year = new ReleaseYearEntity(2002L);

        MovieEntity movie = new MovieEntity(1L, "Spider-Man", year);
        movie.setGenres(List.of(
            new GenreEntity("Action"),
            new GenreEntity("Adventure")
        ));
        movie.setActors(List.of(
            new ActorEntity("Tobey", "Maguire"),
            new ActorEntity("Kirsten", "Dunst")
        ));

        year.setMovies(List.of(movie));

        assertThat(year, is(instanceOf(ReleaseYearEntity.class)));
        assertThat(year.getClass().getDeclaredFields().length, is(equalTo(2)));

    }

    @Test
    void testReleaseYearEntity() {
        ReleaseYearEntity year = new ReleaseYearEntity(2002L);

        MovieEntity movie = new MovieEntity(1L, "Spider-Man", year);
        
        movie.setGenres(List.of(
            new GenreEntity("Action"),
            new GenreEntity("Adventure")
        ));
        movie.setActors(List.of(
            new ActorEntity("Tobey", "Maguire"),
            new ActorEntity("Kirsten", "Dunst")
        ));

        List<MovieEntity> movies = List.of(movie);
        year.setMovies(movies);


        assertThat(year.getId(), is(equalTo(2002L)));
        assertThat(year.getMovies(), is(equalTo(movies)));

    }

}

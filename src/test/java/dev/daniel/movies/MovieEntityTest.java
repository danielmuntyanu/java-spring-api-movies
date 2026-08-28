package dev.daniel.movies;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.Test;

import dev.daniel.actors.ActorEntity;
import dev.daniel.genres.GenreEntity;
import dev.daniel.years.ReleaseYearEntity;

public class MovieEntityTest {

    @Test
    void testMovieEntity_Initialization() {

        MovieEntity movie = new MovieEntity(
            1L,
            "Spider-Man",
            new ReleaseYearEntity(2002L)
        );
        movie.setGenres(List.of(
            new GenreEntity("Action"),
            new GenreEntity("Adventure")
        ));
        movie.setActors(List.of(
            new ActorEntity("Tobey", "Maguire"),
            new ActorEntity("Kirsten", "Dunst")
        ));

        assertThat(movie, is(instanceOf(MovieEntity.class)));
        assertThat(movie.getClass().getDeclaredFields().length, is(equalTo(5)));
    }

    @Test
    void testMovieEntity() {

        MovieEntity movie = new MovieEntity(
            1L,
            "Spider-Man",
            new ReleaseYearEntity(2002L)
        );

        List<GenreEntity> genres = List.of(
            new GenreEntity("Action"),
            new GenreEntity("Adventure")
        );
        movie.setGenres(genres);

        List<ActorEntity> actors = List.of(
            new ActorEntity("Tobey", "Maguire"),
            new ActorEntity("Kirsten", "Dunst")
        );
        movie.setActors(actors);

        assertThat(movie.getId(), is(equalTo(1L)));
        assertThat(movie.getTitle(), is(equalTo("Spider-Man")));
        assertThat(movie.getGenres(), is(equalTo(genres)));
        assertThat(movie.getActors(), is(equalTo(actors)));

    }

}

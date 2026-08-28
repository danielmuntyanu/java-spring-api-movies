package dev.daniel.actors;

import org.junit.jupiter.api.Test;

import dev.daniel.genres.GenreEntity;
import dev.daniel.movies.MovieEntity;
import dev.daniel.years.ReleaseYearEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.List;

public class ActorEntityTest {

    @Test
    void testActorEntity_Initialization() {

        ActorEntity actor = new ActorEntity(1L, "Tobey", "Maguire");

        ReleaseYearEntity year = new ReleaseYearEntity(2002L);
        MovieEntity movie = new MovieEntity(1L, "Spider-Man", year);
        movie.setGenres(List.of(
            new GenreEntity("Action"),
            new GenreEntity("Adventure")
        ));
        movie.setActors(List.of(
            actor,
            new ActorEntity("Kirsten", "Dunst")
        ));

        actor.setMovies(List.of(movie));

        assertThat(actor, is(instanceOf(ActorEntity.class)));
        assertThat(actor.getClass().getDeclaredFields().length, is(equalTo(4)));

    }

    @Test
    void testGenreEntity() {

        ActorEntity actor = new ActorEntity(1L, "Tobey", "Maguire");

        ReleaseYearEntity year = new ReleaseYearEntity(2002L);
        MovieEntity movie = new MovieEntity(1L, "Spider-Man", year);
        movie.setGenres(List.of(
            new GenreEntity("Action"),
            new GenreEntity("Adventure")
        ));
        movie.setActors(List.of(
            actor, 
            new ActorEntity("Kirsten", "Dunst")
        ));

        List<MovieEntity> movies = List.of(movie);
        actor.setMovies(movies);


        assertThat(actor.getId(), is(equalTo(1L)));
        assertThat(actor.getFirstName(), is(equalTo("Tobey")));
        assertThat(actor.getLastName(), is(equalTo("Maguire")));
        assertThat(actor.getMovies(), is(equalTo(movies)));

    }

}

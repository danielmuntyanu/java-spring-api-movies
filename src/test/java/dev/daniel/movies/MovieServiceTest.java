package dev.daniel.movies;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.daniel.actors.ActorsRepository;
import dev.daniel.genres.GenresRepository;
import dev.daniel.movies.dtos.MovieDTORequest;
import dev.daniel.movies.dtos.MovieDTOResponse;
import dev.daniel.years.ReleaseYearEntity;
import dev.daniel.years.YearsRepository;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @InjectMocks
    MovieService service;

    @Mock
    MovieRepository movieRepository;

    @Mock
    YearsRepository yearsRepository;

    @Mock
    GenresRepository genresRepository;

    @Mock
    ActorsRepository actorsRepository;


    @BeforeEach
    void setup() {
        service = new MovieService(movieRepository, yearsRepository, genresRepository, actorsRepository);
    }

    @Test
    void testGetEntities() {

        List<MovieEntity> moviesMock = List.of(
            new MovieEntity(1L, "Spider-Man", new ReleaseYearEntity(2002L)),
            new MovieEntity(2L, "Spider-Man 2", new ReleaseYearEntity(2004L))
        );

        when(movieRepository.findAll()).thenReturn(moviesMock);
        List<MovieDTOResponse> moviesRecieved = service.getEntities();

        assertThat(moviesRecieved.size(), is(equalTo(2)));
        assertThat(moviesRecieved.get(0).title(), is(equalTo("Spider-Man")));
        assertThat(moviesRecieved.get(1).id(), is(equalTo(2L)));
    }

    @Test
    void testGetById() {
        MovieEntity movieMock = new MovieEntity(1L, "Spider-Man", new ReleaseYearEntity(2002L));

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movieMock));
        MovieDTOResponse movieRecieved = service.getById(1L);

        assertThat(movieRecieved.id(), is(equalTo(1L)));
        assertThat(movieRecieved.title(), is(equalTo("Spider-Man")));
    }

}

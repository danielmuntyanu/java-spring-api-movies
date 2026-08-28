package dev.daniel.genres;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.daniel.genres.dtos.GenreDTOResponse;
import dev.daniel.implementations.InterfaceGenericGetService;
import dev.daniel.movies.dtos.MovieDTOResponse;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = GenresController.class)
public class GenresControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    InterfaceGenericGetService<GenreDTOResponse> service;

    @Autowired
    ObjectMapper mapper;


    @Test
    void testIndex_shouldReturnAllGenres() throws Exception {
        
        List<GenreDTOResponse> genresMock = new ArrayList<>();

        MovieDTOResponse movie1 = new MovieDTOResponse(1L, "Spider-Man", 2002L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        MovieDTOResponse movie2 = new MovieDTOResponse(2L, "Spider-Man 2", 2004L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        MovieDTOResponse movie3 = new MovieDTOResponse(3L, "Spider-Man 3", 2007L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        
        GenreDTOResponse genre1 = new GenreDTOResponse(1L, "Adventure", List.of(movie1, movie2, movie3));
        GenreDTOResponse genre2 = new GenreDTOResponse(2L, "Action", List.of(movie1, movie2, movie3));
        genresMock.add(genre1);
        genresMock.add(genre2);

        String json = mapper.writeValueAsString(genresMock);

        when(service.getEntities()).thenReturn(genresMock);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/genres"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

        List<GenreDTOResponse> genresReceived = mapper.readValue(
            response.getContentAsString(), 
            new TypeReference<List<GenreDTOResponse>>(){}
        );
        
        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(genresReceived, is(equalTo(genresMock)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }

    @Test 
    void testFindById_ShouldReturnGenreById() throws Exception {
        
        MovieDTOResponse movie = new MovieDTOResponse(1L, "Spider-Man", 2002L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        GenreDTOResponse dto = new GenreDTOResponse(1L, "Adventure", List.of(movie));
        String json = mapper.writeValueAsString(dto);

        when(service.getById(1L)).thenReturn(dto);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/genres/1"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), is(equalTo(json)));
        assertThat(response.getContentAsString(), containsString("Spider-Man"));
        assertThat(response.getContentAsString(), containsString("Adventure"));
    }

    @Test
    void testSearchByName_shouldReturnFoundedGenres() throws Exception {

        List<GenreDTOResponse> genresMock = new ArrayList<>();

        MovieDTOResponse movie1 = new MovieDTOResponse(1L, "The Godfather", 1974L, List.of("Drama", "Criminal"), List.of("Marlon Brando", "Al Pacino"));
        MovieDTOResponse movie2 = new MovieDTOResponse(2L, "The Godfather 2", 1982L, List.of("Drama", "Criminal"), List.of("Marlon Brando", "Al Pacino"));
        GenreDTOResponse genreDrama = new GenreDTOResponse(3L, "Drama", List.of(movie1, movie2));        
        genresMock.add(genreDrama);
        String json = mapper.writeValueAsString(genresMock);

        String expectedGenre = "Drama";
        when(service.getByName(expectedGenre)).thenReturn(genresMock);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/genres/search")
            .param("genreName", expectedGenre)
            .accept(MediaType.ALL_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        List<GenreDTOResponse> genresReceived = mapper.readValue(
            response.getContentAsString(), 
            new TypeReference<List<GenreDTOResponse>>(){}
        );
        
        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(genresReceived, is(equalTo(genresMock)));
        assertThat(response.getContentAsString(), is(equalTo(json)));

    }

}

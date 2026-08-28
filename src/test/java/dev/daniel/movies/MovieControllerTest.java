package dev.daniel.movies;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.daniel.implementations.InterfaceGenericEditService;
import dev.daniel.implementations.InterfaceGenericGetService;
import dev.daniel.movies.dtos.MovieDTORequest;
import dev.daniel.movies.dtos.MovieDTOResponse;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = MovieController.class)
public class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    InterfaceGenericGetService<MovieDTOResponse> getService;

    @MockitoBean
    InterfaceGenericEditService<MovieDTORequest, MovieDTOResponse> editService;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testIndex_ShouldReturnAllMovies() throws Exception {

        List<MovieDTOResponse> moviesMock = new ArrayList<>();
        MovieDTOResponse movie1 = new MovieDTOResponse(1L, "Spider-Man", 2002L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        MovieDTOResponse movie2 = new MovieDTOResponse(1L, "Spider-Man 2", 2004L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        MovieDTOResponse movie3 = new MovieDTOResponse(1L, "Spider-Man 3", 2007L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        moviesMock.add(movie1);
        moviesMock.add(movie2);
        moviesMock.add(movie3);
        String json = mapper.writeValueAsString(moviesMock);

        when(getService.getEntities()).thenReturn(moviesMock);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/movies"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

        List<MovieDTOResponse> moviesReceived = mapper.readValue(
            response.getContentAsString(), 
            new TypeReference<List<MovieDTOResponse>>(){}
        );
        
        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(moviesReceived, is(equalTo(moviesMock)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }

    @Test 
    void testGetById_ShouldReturnMovieById() throws Exception {

        MovieDTOResponse dto = new MovieDTOResponse(1L, "Spider-Man", 2002L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        String json = mapper.writeValueAsString(dto);

        when(getService.getById(1L)).thenReturn(dto);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/movies/1"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), is(equalTo(json)));
        assertThat(response.getContentAsString(), containsString("Spider-Man"));
    }

}

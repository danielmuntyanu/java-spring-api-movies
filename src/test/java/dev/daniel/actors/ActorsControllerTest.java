package dev.daniel.actors;

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

import dev.daniel.actors.dtos.ActorDTOResponse;
import dev.daniel.implementations.InterfaceGenericGetService;
import dev.daniel.movies.dtos.MovieDTOResponse;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = ActorsController.class)
public class ActorsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    InterfaceGenericGetService<ActorDTOResponse> service;

    @Autowired
    ObjectMapper mapper;


    @Test
    void testIndex_shouldReturnAllActors() throws Exception {
        
        List<ActorDTOResponse> actorsMock = new ArrayList<>();

        MovieDTOResponse movie1 = new MovieDTOResponse(1L, "Spider-Man", 2002L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        MovieDTOResponse movie2 = new MovieDTOResponse(2L, "Spider-Man 2", 2004L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        MovieDTOResponse movie3 = new MovieDTOResponse(3L, "Spider-Man 3", 2007L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        
        ActorDTOResponse genre1 = new ActorDTOResponse(1L, "Tobey Maguire", List.of(movie1, movie2, movie3));
        ActorDTOResponse genre2 = new ActorDTOResponse(2L, "Kirsten Dunst", List.of(movie1, movie2, movie3));
        actorsMock.add(genre1);
        actorsMock.add(genre2);

        String json = mapper.writeValueAsString(actorsMock);

        when(service.getEntities()).thenReturn(actorsMock);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/actors"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

        List<ActorDTOResponse> actorsReceived = mapper.readValue(
            response.getContentAsString(), 
            new TypeReference<List<ActorDTOResponse>>(){}
        );
        
        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(actorsReceived, is(equalTo(actorsMock)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }

    @Test 
    void testFindById_ShouldReturnActorById() throws Exception {
        
        MovieDTOResponse movie = new MovieDTOResponse(1L, "Spider-Man", 2002L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        ActorDTOResponse dto = new ActorDTOResponse(1L, "Tobey Maguire", List.of(movie));
        String json = mapper.writeValueAsString(dto);

        when(service.getById(1L)).thenReturn(dto);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/actors/1"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), is(equalTo(json)));
        assertThat(response.getContentAsString(), containsString("Spider-Man"));
        assertThat(response.getContentAsString(), containsString("Tobey Maguire"));
    }

    @Test
    void testSearchByName_shouldReturnExactActor() throws Exception {

        MovieDTOResponse movie1 = new MovieDTOResponse(1L, "The Godfather", 1974L, List.of("Drama", "Criminal"), List.of("Marlon Brando", "Al Pacino"));
        MovieDTOResponse movie2 = new MovieDTOResponse(2L, "The Godfather 2", 1982L, List.of("Drama", "Criminal"), List.of("Marlon Brando", "Al Pacino"));
        ActorDTOResponse dto = new ActorDTOResponse(1L, "Al Pacino", List.of(movie1, movie2));        
        String json = mapper.writeValueAsString(dto);

        String expectedGenre = "Al Pacino";
        when(service.getByName(expectedGenre)).thenReturn(List.of(dto));
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/actors/search")
            .param("fullname", expectedGenre)
            .accept(MediaType.ALL_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        ActorDTOResponse actorRecieved = mapper.readValue(response.getContentAsString(), ActorDTOResponse.class);

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(actorRecieved, is(equalTo(dto)));
        assertThat(response.getContentAsString(), is(equalTo(json)));

    }

}

package dev.daniel.years;

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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.daniel.implementations.InterfaceGenericGetService;
import dev.daniel.movies.dtos.MovieDTOResponse;
import dev.daniel.years.dtos.ReleaseYearDTOResponse;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = YearsController.class)
public class YearsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    InterfaceGenericGetService<ReleaseYearDTOResponse> service;

    @Autowired
    ObjectMapper mapper;


    @Test
    void testIndex_shouldReturnAllYears() throws Exception {
        
        List<ReleaseYearDTOResponse> yearsMock = new ArrayList<>();

        MovieDTOResponse movie1 = new MovieDTOResponse(1L, "Spider-Man", 2002L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        MovieDTOResponse movie2 = new MovieDTOResponse(2L, "Spider-Man 2", 2004L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        MovieDTOResponse movie3 = new MovieDTOResponse(3L, "Spider-Man 3", 2007L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        
        ReleaseYearDTOResponse year1 = new ReleaseYearDTOResponse(2002L, List.of(movie1));
        ReleaseYearDTOResponse year2 = new ReleaseYearDTOResponse(2004L, List.of(movie2));
        ReleaseYearDTOResponse year3 = new ReleaseYearDTOResponse(2007L, List.of(movie3));
        yearsMock.add(year1);
        yearsMock.add(year2);
        yearsMock.add(year3);

        String json = mapper.writeValueAsString(yearsMock);

        when(service.getEntities()).thenReturn(yearsMock);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/years"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

        List<ReleaseYearDTOResponse> yearsReceived = mapper.readValue(
            response.getContentAsString(), 
            new TypeReference<List<ReleaseYearDTOResponse>>(){}
        );
        
        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(yearsReceived, is(equalTo(yearsMock)));
        assertThat(response.getContentAsString(), is(equalTo(json)));
    }

    @Test 
    void testFindById_ShouldReturnReleaseYearByYear() throws Exception {
        
        MovieDTOResponse movie = new MovieDTOResponse(1L, "Spider-Man", 2002L, List.of("Adventure", "Action"), List.of("Tobey Maguire", "Kirsten Dunst"));
        ReleaseYearDTOResponse dto = new ReleaseYearDTOResponse(2002L, List.of(movie));
        String json = mapper.writeValueAsString(dto);

        when(service.getById(2002L)).thenReturn(dto);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/years/2002"))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), is(equalTo(json)));
        assertThat(response.getContentAsString(), containsString("Spider-Man"));
    }

}

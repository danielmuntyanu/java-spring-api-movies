package dev.daniel.home;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testIndex_ShouldReturnHelloMessage() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(get(""))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus(), is(equalTo(HttpStatus.OK.value())));
        assertThat(response.getContentAsString(), containsString("Hello, dear guest"));
    }



}

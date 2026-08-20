package dev.daniel.movies;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "${api-endpoint}/movies")
public class MovieController {

    @GetMapping("")
    public MovieEntity index() {
        return new MovieEntity(1L, "Spider-Man");
    }

}

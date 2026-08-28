package dev.daniel.home;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

    @GetMapping("")
    public String index() {
        return "Hello, dear guest! Go to https://github.com/danielmuntyanu/java-spring-api-movies to read docs to explore endpoints.";
    }

}

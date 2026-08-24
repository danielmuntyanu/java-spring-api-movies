package dev.daniel.movies.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieDTORequest(
    @NotBlank(message = "Title cannot be empty") 
    @NotNull(message = "Title cannot be null") 
    String title, 
    @NotNull(message = "Release year cannot be null") 
    int release_year
) {

}

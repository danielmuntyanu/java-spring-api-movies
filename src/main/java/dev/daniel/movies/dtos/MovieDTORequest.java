package dev.daniel.movies.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MovieDTORequest(
    @NotBlank(message = "Title cannot be empty") 
    @NotNull(message = "Title cannot be null") 
    String title, 
    @NotBlank(message = "Release year cannot be empty") 
    @NotNull(message = "Release year cannot be null") 
    int release_year
) {

}

package dev.daniel.movies.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.List;



public record MovieDTORequest(
    @NotBlank(message = "Title cannot be empty") 
    @NotNull(message = "Title cannot be null") 
    String title, 
    
    @NotNull(message = "Release year cannot be null") 
    @Max(value = 2100, message = "Entry correct year from 1900 to 2100")
    @Min(value = 1900, message = "Entry correct year from 1900 to 2100")
    Long release_year,
    
    List<String> genres,

    List<String> actors

) {

}

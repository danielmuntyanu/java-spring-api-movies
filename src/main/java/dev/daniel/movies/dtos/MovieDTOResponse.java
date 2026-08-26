package dev.daniel.movies.dtos;

import java.util.List;

public record MovieDTOResponse(
    Long id, 
    String title, 
    Long release_year,
    List<String> genres,
    List<String> actors
) {
} 
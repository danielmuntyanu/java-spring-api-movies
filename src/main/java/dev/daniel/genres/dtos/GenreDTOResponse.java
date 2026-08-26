package dev.daniel.genres.dtos;

import java.util.List;

import dev.daniel.movies.dtos.MovieDTOResponse;

public record GenreDTOResponse(
    Long id, String name, List<MovieDTOResponse> movies
) {

}

package dev.daniel.actors.dtos;

import java.util.List;

import dev.daniel.movies.dtos.MovieDTOResponse;

public record ActorDTOResponse(
    Long id, String fullname, List<MovieDTOResponse> movies
) {

}

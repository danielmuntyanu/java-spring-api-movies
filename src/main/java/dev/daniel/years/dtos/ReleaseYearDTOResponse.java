package dev.daniel.years.dtos;

import java.util.List;

import dev.daniel.movies.dtos.MovieDTOResponse;

public record ReleaseYearDTOResponse(Long year, List<MovieDTOResponse> movies) {
}



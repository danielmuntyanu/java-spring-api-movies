package dev.daniel.years.mappers;

import java.util.ArrayList;
import java.util.List;

import dev.daniel.movies.dtos.MovieDTOResponse;
import dev.daniel.movies.mappers.MovieMapper;
import dev.daniel.years.ReleaseYearEntity;
import dev.daniel.years.dtos.ReleaseYearDTOResponse;

public class ReleaseYearMapper {

    public static ReleaseYearDTOResponse toDTO(ReleaseYearEntity entity) {
        List<MovieDTOResponse> movies = new ArrayList<>();

        entity.getMovies().forEach(m -> {
            movies.add(MovieMapper.toDTO(m));
        });

        return new ReleaseYearDTOResponse(entity.getId(), movies);
    }

}

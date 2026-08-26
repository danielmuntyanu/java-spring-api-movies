package dev.daniel.genres.mappers;

import java.util.ArrayList;
import java.util.List;

import dev.daniel.genres.GenreEntity;
import dev.daniel.genres.dtos.GenreDTOResponse;
import dev.daniel.movies.dtos.MovieDTOResponse;
import dev.daniel.movies.mappers.MovieMapper;

public class GenreMapper {
    public static GenreDTOResponse toDTO(GenreEntity entity) {
        List<MovieDTOResponse> movies = new ArrayList<>();
        
        if (entity.getMovies() != null) {
            entity.getMovies().forEach(m -> {
                MovieDTOResponse dto = MovieMapper.toDTO(m);
                movies.add(dto);
            });
        }

        return new GenreDTOResponse(entity.getId(), entity.getName(), movies);
    }
}

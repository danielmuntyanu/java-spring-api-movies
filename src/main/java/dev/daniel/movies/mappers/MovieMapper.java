package dev.daniel.movies.mappers;

import dev.daniel.movies.MovieEntity;
import dev.daniel.movies.dtos.MovieDTORequest;
import dev.daniel.movies.dtos.MovieDTOResponse;

public class MovieMapper {
    
    public static MovieEntity toEntity(MovieDTORequest dto) {
        MovieEntity entity = new MovieEntity();
        entity.setTitle(dto.title());
        entity.setRelease_year(dto.release_year());
        return entity;
    }

    public static MovieDTOResponse teDTO(MovieEntity entity) {
        return new MovieDTOResponse(entity.getId(), entity.getTitle(), entity.getRelease_year());
    }

}

package dev.daniel.movies.mappers;

import dev.daniel.movies.MovieEntity;
import dev.daniel.movies.dtos.MovieDTORequest;
import dev.daniel.movies.dtos.MovieDTOResponse;
import dev.daniel.years.ReleaseYearEntity;

public class MovieMapper {
    
    public static MovieEntity toEntity(MovieDTORequest dto, ReleaseYearEntity year) {
        MovieEntity entity = new MovieEntity();
        entity.setTitle(dto.title());
        entity.setReleaseYear(year);
        return entity;
    }

    public static MovieDTOResponse toDTO(MovieEntity entity) {
        return new MovieDTOResponse(entity.getId(), entity.getTitle(), entity.getReleaseYear().getId());
    }

}

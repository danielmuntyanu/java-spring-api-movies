package dev.daniel.movies.mappers;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.ArrayList;
import java.util.List;

import dev.daniel.actors.ActorEntity;
import dev.daniel.genres.GenreEntity;
import dev.daniel.movies.MovieEntity;
import dev.daniel.movies.dtos.MovieDTORequest;
import dev.daniel.movies.dtos.MovieDTOResponse;
import dev.daniel.years.ReleaseYearEntity;

public class MovieMapper {
    
    public static MovieEntity toEntity(MovieDTORequest dto, ReleaseYearEntity year, List<GenreEntity> genres, List<ActorEntity> actors) {
        MovieEntity entity = new MovieEntity();
        entity.setTitle(dto.title());
        entity.setReleaseYear(year);
        entity.setGenres(genres);
        entity.setActors(actors);

        return entity;
    }

    public static MovieDTOResponse toDTO(MovieEntity entity) {
        List<String> genres = new ArrayList<>();
        List<String> actors = new ArrayList<>();
        
        if (entity.getGenres() != null) {
            entity.getGenres().forEach(g -> {
                String genreName = g.getName();
                genres.add(genreName);
            });
        }

        if (entity.getActors() != null) {
            entity.getActors().forEach(a -> {
                String fullname = a.getFirstName() + " " + a.getLastName();
                actors.add(fullname);
            });
        }

        return new MovieDTOResponse(
            entity.getId(), 
            entity.getTitle(), 
            entity.getReleaseYear().getId(),
            genres,
            actors
        );
    }

}

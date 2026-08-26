package dev.daniel.actors.mappers;

import java.util.ArrayList;
import java.util.List;

import dev.daniel.actors.ActorEntity;
import dev.daniel.actors.dtos.ActorDTOResponse;
import dev.daniel.movies.dtos.MovieDTOResponse;
import dev.daniel.movies.mappers.MovieMapper;

public class ActorMapper {

    public static ActorDTOResponse toDTO(ActorEntity entity) {
        List<MovieDTOResponse> movies = new ArrayList<>();

        if (entity.getMovies() != null) {
            entity.getMovies().forEach(m -> {
                MovieDTOResponse dto = MovieMapper.toDTO(m);
                movies.add(dto);
            });
        }

        return new ActorDTOResponse(
            entity.getId(),
            entity.getFirstName() + " " + entity.getLastName(),
            movies
        );
    }

}

package dev.daniel.actors.mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.groups.Tuple;

import dev.daniel.actors.ActorEntity;
import dev.daniel.actors.dtos.ActorDTOResponse;
import dev.daniel.actors.dtos.Fullname;
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

    public static Fullname toFullnameVO(String fullname) {
        String[] names = fullname.split(" ");
        String firstName = names[0];
        String lastName;
        if (names.length > 1) {
            String[] sliced = Arrays.copyOfRange(names, 1, names.length);
            lastName = String.join(" ", sliced);
        } else {
            lastName = "";
        }
        
        return new Fullname(firstName, lastName);
    }

}

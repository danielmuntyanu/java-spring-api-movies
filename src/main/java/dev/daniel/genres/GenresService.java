package dev.daniel.genres;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.daniel.genres.dtos.GenreDTOResponse;
import dev.daniel.genres.mappers.GenreMapper;
import dev.daniel.implementations.InterfaceGenericGetService;
import dev.daniel.movies.exceptions.MovieExceptionNotFound;

@Service
public class GenresService implements InterfaceGenericGetService<GenreDTOResponse> {

    private final GenresRepository genresRespository;

    public GenresService(GenresRepository genresRespository) {
        this.genresRespository = genresRespository;
    }


    @Override
    public List<GenreDTOResponse> getEntities() {
        List<GenreDTOResponse> genres = new ArrayList<>();

        genresRespository.findAll().forEach(g -> {
            GenreDTOResponse dto = GenreMapper.toDTO(g);
            genres.add(dto);
        });
        return genres;
    }

    @Override
    public GenreDTOResponse getById(Long id) {
        
        GenreEntity genre = genresRespository.findById(id)
            .orElseThrow(() -> new MovieExceptionNotFound("Cannot find genre with id " + id + " because it doesn't exist."));

        return GenreMapper.toDTO(genre);
    }

    @Override
    public List<GenreDTOResponse> getByName(String name) {
        List<GenreDTOResponse> genres = genresRespository.findByNameStartingWith(name)
            .stream().map(g -> GenreMapper.toDTO(g)).toList();

        return genres;
    }

}

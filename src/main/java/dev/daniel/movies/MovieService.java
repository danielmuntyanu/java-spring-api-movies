package dev.daniel.movies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import dev.daniel.implementations.InterfaceGenericEditService;
import dev.daniel.implementations.InterfaceGenericGetService;
import dev.daniel.movies.dtos.MovieDTORequest;
import dev.daniel.movies.dtos.MovieDTOResponse;
import dev.daniel.movies.mappers.MovieMapper;


@Service
public class MovieService implements InterfaceGenericGetService<MovieDTOResponse>, InterfaceGenericEditService<MovieDTORequest, MovieDTOResponse> {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MovieDTOResponse> getEntities() {
        List<MovieDTOResponse> movies = new ArrayList<>();   
        
        repository.findAll().forEach(m -> {
            MovieDTOResponse movie = MovieMapper.teDTO(m);
            movies.add(movie);
        });

        return movies;
    }

    @Override
    public MovieDTOResponse getById(Long id) {
        MovieEntity entity = repository.findById(id).orElseThrow();
        return MovieMapper.teDTO(entity);
    }

    @Override
    public MovieDTOResponse storeEntity(MovieDTORequest dto) {
        
        MovieEntity movieToSave = MovieMapper.toEntity(dto);

        // check if it already exists
        Example<MovieEntity> example = Example.of(movieToSave);
        boolean isEmpty = repository.findAll(example).isEmpty();
        if (!isEmpty) return null;
        
        MovieEntity movieSaved = repository.save(movieToSave);

        return MovieMapper.teDTO(movieSaved);

    }

    
    
}

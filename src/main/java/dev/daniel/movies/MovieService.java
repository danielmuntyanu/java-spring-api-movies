package dev.daniel.movies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import dev.daniel.implementations.InterfaceGenericEditService;
import dev.daniel.implementations.InterfaceGenericGetService;
import dev.daniel.movies.dtos.MovieDTORequest;
import dev.daniel.movies.dtos.MovieDTOResponse;
import dev.daniel.movies.exceptions.MovieExceptionNotFound;
import dev.daniel.movies.mappers.MovieMapper;
import jakarta.transaction.Transactional;


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
        MovieEntity entity = repository.findById(id)
            .orElseThrow(() -> new MovieExceptionNotFound("Movie not found. Id " + id + " doesn't exist."));
        return MovieMapper.teDTO(entity);
    }

    @Override
    @Transactional
    public MovieDTOResponse storeEntity(MovieDTORequest dto) {
        
        MovieEntity movieToSave = MovieMapper.toEntity(dto);

        // check if it already exists
        Example<MovieEntity> example = Example.of(movieToSave);
        boolean isEmpty = repository.findAll(example).isEmpty();
        if (!isEmpty) return null;
        
        MovieEntity movieSaved = repository.save(movieToSave);

        return MovieMapper.teDTO(movieSaved);

    }

    @Override
    @Transactional
    public MovieDTOResponse updateEntity(Long id, MovieDTORequest dto) {
        
        MovieEntity movie = repository.findById(id)
            .orElseThrow(() -> new MovieExceptionNotFound("Cannot update because movie not found. Id " + id + " doesn't exist."));

        movie.setTitle(dto.title());
        movie.setRelease_year(dto.release_year());

        MovieEntity updated = repository.save(movie);

        return MovieMapper.teDTO(updated);
        
    }

    @Override
    @Transactional
    public void deleteEntity(Long id) {
        boolean exists = repository.existsById(id);
        if (!exists) throw new MovieExceptionNotFound("Cannot delete because movie not found. Id " + id + " doesn't exist.");

        repository.deleteById(id);
    }

    
    
}

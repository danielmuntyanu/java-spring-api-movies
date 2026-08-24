package dev.daniel.movies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import dev.daniel.implementations.InterfaceGenericEditService;
import dev.daniel.implementations.InterfaceGenericGetService;
import dev.daniel.movies.dtos.MovieDTORequest;
import dev.daniel.movies.dtos.MovieDTOResponse;
import dev.daniel.movies.exceptions.MovieExceptionConflict;
import dev.daniel.movies.exceptions.MovieExceptionNotFound;
import dev.daniel.movies.mappers.MovieMapper;
import dev.daniel.years.ReleaseYearEntity;
import dev.daniel.years.YearsRepository;
import jakarta.transaction.Transactional;


@Service
public class MovieService implements InterfaceGenericGetService<MovieDTOResponse>, InterfaceGenericEditService<MovieDTORequest, MovieDTOResponse> {

    private final MovieRepository movieRepository;
    private final YearsRepository yearsRepository;

    public MovieService(MovieRepository movieRepository, YearsRepository yearsRepository) {
        this.movieRepository = movieRepository;
        this.yearsRepository = yearsRepository;
    }

    @Override
    public List<MovieDTOResponse> getEntities() {
        List<MovieDTOResponse> movies = new ArrayList<>();   
        
        movieRepository.findAll().forEach(m -> {
            MovieDTOResponse movieDTO = MovieMapper.teDTO(m);
            movies.add(movieDTO);
        });

        return movies;
    }

    @Override
    public MovieDTOResponse getById(Long id) {
        MovieEntity entity = movieRepository.findById(id)
            .orElseThrow(() -> new MovieExceptionNotFound("Movie not found. Id " + id + " doesn't exist."));
        return MovieMapper.teDTO(entity);
    }

    @Override
    @Transactional
    public MovieDTOResponse storeEntity(MovieDTORequest dto) {
        
        ReleaseYearEntity year = resolveYear(dto.release_year());
        MovieEntity movieToSave = MovieMapper.toEntity(dto, year);

        // check if it already exists
        Example<MovieEntity> example = Example.of(movieToSave);
        boolean isEmpty = movieRepository.findAll(example).isEmpty();
        if (!isEmpty) throw new MovieExceptionConflict("It already has this movie");
        
        MovieEntity movieSaved = movieRepository.save(movieToSave);

        return MovieMapper.teDTO(movieSaved);
    }

    private ReleaseYearEntity resolveYear(int year) {
        return yearsRepository.findById(year)
        .orElseGet(() -> 
            yearsRepository.save(new ReleaseYearEntity(year)));
    }

    @Override
    @Transactional
    public MovieDTOResponse updateEntity(Long id, MovieDTORequest dto) {
        
        MovieEntity movie = movieRepository.findById(id)
            .orElseThrow(() -> new MovieExceptionNotFound("Cannot update because movie not found. Id " + id + " doesn't exist."));

        movie.setTitle(dto.title());
        movie.setReleaseYear(
            new ReleaseYearEntity(dto.release_year())
        );

        MovieEntity updated = movieRepository.save(movie);

        return MovieMapper.teDTO(updated);
        
    }

    @Override
    @Transactional
    public void deleteEntity(Long id) {
        boolean exists = movieRepository.existsById(id);
        if (!exists) throw new MovieExceptionNotFound("Cannot delete because movie not found. Id " + id + " doesn't exist.");

        movieRepository.deleteById(id);
    }


    @Override
    public List<MovieDTOResponse> getAllByYear(int year) {
        List<MovieDTOResponse> movies = new ArrayList<>();

        movieRepository.findByReleaseYear_Id(year).forEach(m -> {
            MovieDTOResponse movieDTO = MovieMapper.teDTO(m);
            movies.add(movieDTO);
        });

        return movies;
    }

    
    
}

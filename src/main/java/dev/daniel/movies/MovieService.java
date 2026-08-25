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
            MovieDTOResponse movieDTO = MovieMapper.toDTO(m);
            movies.add(movieDTO);
        });

        return movies;
    }

    @Override
    public MovieDTOResponse getById(Long id) {
        MovieEntity entity = movieRepository.findById(id)
            .orElseThrow(() -> new MovieExceptionNotFound("Movie not found. Id " + id + " doesn't exist."));
        return MovieMapper.toDTO(entity);
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

        return MovieMapper.toDTO(movieSaved);
    }

    private ReleaseYearEntity resolveYear(Long year) {
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
            resolveYear(dto.release_year())
        );

        MovieEntity updated = movieRepository.save(movie);

        return MovieMapper.toDTO(updated);
        
    }

    @Override
    @Transactional
    public void deleteEntity(Long id) {
        MovieEntity movie = movieRepository.findById(id)
            .orElseThrow(() -> new MovieExceptionNotFound("Cannot delete because movie not found. Id " + id + " doesn't exist."));

        ReleaseYearEntity year = movie.getReleaseYear();

        movieRepository.deleteById(id);
        movieRepository.flush();

        if (year.getMovies().isEmpty()) {
            yearsRepository.deleteById(year.getId());
        }
    }
    
}

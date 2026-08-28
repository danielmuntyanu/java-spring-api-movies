package dev.daniel.movies;

import dev.daniel.genres.GenresRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import dev.daniel.actors.ActorEntity;
import dev.daniel.actors.ActorsRepository;
import dev.daniel.actors.dtos.Fullname;
import dev.daniel.actors.mappers.ActorMapper;
import dev.daniel.genres.GenreEntity;
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

    private final GenresRepository genresRepository;
    private final MovieRepository movieRepository;
    private final YearsRepository yearsRepository;
    private final ActorsRepository actorsRepository;

    public MovieService(MovieRepository movieRepository, YearsRepository yearsRepository, GenresRepository genresRepository, ActorsRepository actorsRepository) {
        this.movieRepository = movieRepository;
        this.yearsRepository = yearsRepository;
        this.genresRepository = genresRepository;
        this.actorsRepository = actorsRepository;
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

        List<GenreEntity> genres = dto.genres()!=null ? resolveGenres(dto.genres()) : null;
        List<ActorEntity> actors = dto.actors()!=null ? resolveActors(dto.actors()) : null;

        MovieEntity movieToSave = MovieMapper.toEntity(dto, year, genres, actors);

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

    private List<GenreEntity> resolveGenres(List<String> genreNames) {
        List<GenreEntity> genres = new ArrayList<>();
        
        genreNames.forEach(g -> {
            GenreEntity genre = genresRepository.findByName(g)
                .orElseGet(() -> genresRepository.save(new GenreEntity(g))); 
            genres.add(genre);
        });

        return genres;
    }

    private List<ActorEntity> resolveActors(List<String> actorFullNames) {
        List<ActorEntity> actors = new ArrayList<>();

        actorFullNames.forEach(a -> {
            Fullname name = ActorMapper.toFullnameVO(a);

            ActorEntity actor = actorsRepository.getByFirstNameEqualsAndLastNameEquals(name.firstName(), name.lastName())
                .orElseGet(() -> actorsRepository.save(new ActorEntity(name.firstName(), name.lastName())));
            actors.add(actor);
        });

        return actors;
    }

    @Override
    @Transactional
    public MovieDTOResponse updateEntity(Long id, MovieDTORequest dto) {
        
        MovieEntity movie = movieRepository.findById(id)
            .orElseThrow(() -> new MovieExceptionNotFound("Cannot update because movie not found. Id " + id + " doesn't exist."));

        ReleaseYearEntity yearBefore = movie.getReleaseYear();

        movie.setTitle(dto.title());
        movie.setReleaseYear(
            resolveYear(dto.release_year())
        );
        movie.setGenres(
            resolveGenres(dto.genres())
        );
        movie.setActors(
            resolveActors(dto.actors())
        );

        MovieEntity updated = movieRepository.saveAndFlush(movie);

        ReleaseYearEntity yearAfter = movie.getReleaseYear();
        if (yearBefore != yearAfter && (
                yearBefore.getMovies() == null || 
                yearBefore.getMovies().isEmpty()
            ) ) {
            
            yearsRepository.delete(yearBefore);
        }

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

    @Override
    public List<MovieDTOResponse> getByName(String requestString) {
        
        List<MovieEntity> entities = movieRepository.findByTitleContaining(requestString);

        List<MovieDTOResponse> movieDTOs = entities.stream().map((e) -> MovieMapper.toDTO(e)).toList(); 

        return movieDTOs;

    }
    
}

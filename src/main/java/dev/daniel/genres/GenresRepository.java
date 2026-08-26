package dev.daniel.genres;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository extends JpaRepository<GenreEntity, Long> {

    public List<GenreEntity> findByNameStartingWith(String requestString);
    public Optional<GenreEntity> findByName(String name);

}

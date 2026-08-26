package dev.daniel.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    
    public List<MovieEntity> findByTitleContaining(String requestString);

}

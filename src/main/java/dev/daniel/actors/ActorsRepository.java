package dev.daniel.actors;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorsRepository extends JpaRepository<ActorEntity, Long> {
    
    public Optional<ActorEntity> getByFirstNameEqualsAndLastNameEquals(String firstName, String lastName);

}

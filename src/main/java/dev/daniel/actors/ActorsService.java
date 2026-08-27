package dev.daniel.actors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.daniel.actors.dtos.ActorDTOResponse;
import dev.daniel.actors.dtos.Fullname;
import dev.daniel.actors.mappers.ActorMapper;
import dev.daniel.implementations.InterfaceGenericGetService;
import dev.daniel.movies.exceptions.MovieExceptionNotFound;

@Service
public class ActorsService implements InterfaceGenericGetService<ActorDTOResponse> {

    private final ActorsRepository actorsRepository;

    public ActorsService(ActorsRepository actorsRepository) {
        this.actorsRepository = actorsRepository;
    }


    @Override
    public List<ActorDTOResponse> getEntities() {
        List<ActorDTOResponse> actors = new ArrayList<>();

        actorsRepository.findAll().forEach(a -> {
            ActorDTOResponse dto = ActorMapper.toDTO(a);
            actors.add(dto);
        });
        return actors;
    }

    @Override
    public ActorDTOResponse getById(Long id) {
        ActorEntity actor = actorsRepository.findById(id)
            .orElseThrow(() -> new MovieExceptionNotFound(
                "Cannot find actor with id " + id + " because they don't exist."
            ));

        return ActorMapper.toDTO(actor);
    }

    @Override
    public List<ActorDTOResponse> getByName(String fullname) {
        
        Fullname name = ActorMapper.toFullnameVO(fullname);

        ActorEntity actor = actorsRepository.getByFirstNameEqualsAndLastNameEquals(name.firstName(), name.lastName())
            .orElseThrow(() -> new MovieExceptionNotFound(
                "Cannot find actor with name '" + name.firstName() + " " + name.lastName() + "' because they don't exist"
            ));

        return List.of(ActorMapper.toDTO(actor));
    }

    

    

}

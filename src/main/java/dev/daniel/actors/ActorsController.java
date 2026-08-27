package dev.daniel.actors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.daniel.actors.dtos.ActorDTOResponse;
import dev.daniel.implementations.InterfaceGenericGetService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "${api-endpoint}/actors")
public class ActorsController {

    private final InterfaceGenericGetService<ActorDTOResponse> getService;

    public ActorsController(InterfaceGenericGetService<ActorDTOResponse> getService) {
        this.getService = getService;
    }

    @GetMapping("")
    public ResponseEntity<List<ActorDTOResponse>> index() {
        return ResponseEntity.ok(getService.getEntities());
    }
    
    @GetMapping("{id}")
    public ResponseEntity<ActorDTOResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getService.getById(id));
    }

    @GetMapping("search")
    public ResponseEntity<ActorDTOResponse> getByName(@RequestParam String fullname) {
        return ResponseEntity.ok(getService.getByName(fullname).get(0));
    }

}

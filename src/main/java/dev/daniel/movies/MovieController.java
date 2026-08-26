package dev.daniel.movies;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.daniel.implementations.InterfaceGenericEditService;
import dev.daniel.implementations.InterfaceGenericGetService;
import dev.daniel.movies.dtos.MovieDTORequest;
import dev.daniel.movies.dtos.MovieDTOResponse;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "${api-endpoint}/movies")
public class MovieController {

    private final InterfaceGenericGetService<MovieDTOResponse> getService;
    private final InterfaceGenericEditService<MovieDTORequest, MovieDTOResponse> editService;

    public MovieController(InterfaceGenericGetService<MovieDTOResponse> getService, InterfaceGenericEditService<MovieDTORequest, MovieDTOResponse> editService) {
        this.getService = getService;
        this.editService = editService;
    }

    @GetMapping("")
    public ResponseEntity<List<MovieDTOResponse>> index() {
        return ResponseEntity.ok(getService.getEntities());
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieDTOResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<MovieDTOResponse> store(@Valid @RequestBody MovieDTORequest dto) {
        
        MovieDTOResponse dtoResponse = editService.storeEntity(dto);

        if (dtoResponse == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.created(URI.create("/movies/" + dtoResponse.id())).body(dtoResponse);

    }

    @PutMapping("{id}")
    public ResponseEntity<MovieDTOResponse> update(@PathVariable Long id, @Valid @RequestBody MovieDTORequest dto) {
        MovieDTOResponse updated = editService.updateEntity(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        editService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("search")
    public ResponseEntity<List<MovieDTOResponse>> searchByTitle(@RequestParam(name = "title") String requestString) {
        return ResponseEntity.ok(getService.getByName(requestString));
    }
    


}

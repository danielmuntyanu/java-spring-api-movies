package dev.daniel.genres;

import org.springframework.web.bind.annotation.RestController;

import dev.daniel.genres.dtos.GenreDTOResponse;
import dev.daniel.implementations.InterfaceGenericGetService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "${api-endpoint}/genres")
public class GenresController {

    private final InterfaceGenericGetService<GenreDTOResponse> getService;

    public GenresController(InterfaceGenericGetService<GenreDTOResponse> getService) {
        this.getService = getService;
    }

    @GetMapping("")
    public ResponseEntity<List<GenreDTOResponse>> index() {
        return ResponseEntity.ok(getService.getEntities());
    }
    
    @GetMapping("search")
    public ResponseEntity<List<GenreDTOResponse>> searchByName(@RequestParam(name = "genreName") String name) {
        return ResponseEntity.ok(getService.getByName(name));
    }

    @GetMapping("{id}")
    public ResponseEntity<GenreDTOResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getService.getById(id));
    }
    
    

}

package dev.daniel.years;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.daniel.implementations.InterfaceGenericGetService;
import dev.daniel.years.dtos.ReleaseYearDTOResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "${api-endpoint}/years")
public class YearsController {

    private final InterfaceGenericGetService<ReleaseYearDTOResponse> getService;

    public YearsController(InterfaceGenericGetService<ReleaseYearDTOResponse> getService) {
        this.getService = getService;
    }

    @GetMapping("")
    public ResponseEntity<List<ReleaseYearDTOResponse>> getAll() {
        return ResponseEntity.ok(getService.getEntities());
    }
    

    @GetMapping("{year}")
    public ResponseEntity<ReleaseYearDTOResponse> getAllByYear(@Valid @PathVariable Long year) {
        return ResponseEntity.ok(getService.getById(year));
    }

}



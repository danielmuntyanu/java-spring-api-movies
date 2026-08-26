package dev.daniel.years;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.daniel.implementations.InterfaceGenericGetService;
import dev.daniel.movies.exceptions.MovieExceptionNotFound;
import dev.daniel.years.dtos.ReleaseYearDTOResponse;
import dev.daniel.years.mappers.ReleaseYearMapper;

@Service
public class YearsService implements InterfaceGenericGetService<ReleaseYearDTOResponse> {

    private final YearsRepository yearsRepository;

    public YearsService(YearsRepository yearsRepository) {
        this.yearsRepository = yearsRepository;
    }


    @Override
    public List<ReleaseYearDTOResponse> getEntities() {

        List<ReleaseYearDTOResponse> years = new ArrayList<>();   
        
        yearsRepository.findAll().forEach(y -> {
            ReleaseYearDTOResponse yearDTO = ReleaseYearMapper.toDTO(y);
            years.add(yearDTO);
        });

        return years;

    }

    @Override
    public ReleaseYearDTOResponse getById(Long year) {
        
        ReleaseYearEntity entity = yearsRepository.findById(year)
            .orElseThrow(() -> new MovieExceptionNotFound("Year not found. Year " + year + " doesn't exist."));
        return ReleaseYearMapper.toDTO(entity);

    }

    @Override
    public List<ReleaseYearDTOResponse> getByName(String name) {
        //! There is no name columns in 'years' table, but we have to implement this method. 
        return null;
    }
}

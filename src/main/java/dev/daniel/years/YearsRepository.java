package dev.daniel.years;

import org.springframework.data.jpa.repository.JpaRepository;

public interface YearsRepository extends JpaRepository<ReleaseYearEntity, Integer> {

}

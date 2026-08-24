package dev.daniel.years;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "years")
public class ReleaseYearEntity {
    @Id
    private int id;

    public ReleaseYearEntity() {
    }

    public ReleaseYearEntity(int release_year) {
        this.id = release_year;
    }

    public int getId() {
        return id;
    }
    
}

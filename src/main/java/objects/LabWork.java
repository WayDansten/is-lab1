package objects;

import objects.Coordinates;
import objects.Discipline;
import objects.Person;
import objects.types.Difficulty;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="lab_works")
public class LabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value=1)
    private int id;

    @Column(name="name", nullable=false)
    @NotEmpty
    private String name;

    @Column(name="coordinates", nullable=false)
    private Coordinates coordinates;

    @Column(name="creation_date", nullable=false)
    private java.time.LocalDateTime creationDate;

    @Column(name="description", nullable=true)
    private String description;

    @Column(name="difficulty", nullable=true)
    private Difficulty difficulty;

    @Column(name="discipline", nullable=true)
    private Discipline discipline;

    @Column(name="minimal_point", nullable=true)
    @DecimalMin(value=0, inclusive=false)
    private Double minimalPoint;

    @Column(name="average_point", nullable=false)
    @DecimalMin(value=0, inclusive=false)
    private float averagePoint;

    @Column(name="author", nullable=false)
    private Person author;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }
}

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

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "labworks")
public class LabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1)
    private int id;

    @Column(name = "labwork_name", nullable = false)
    @NotEmpty
    private String name;

    @ManyToOne
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    @Column(name = "creation_date", nullable = false)
    private java.time.LocalDateTime creationDate;

    @Column(name = "description", nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = true)
    private Difficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @Column(name = "minimal_point", nullable = true)
    @DecimalMin(value = 0, inclusive = false)
    private Double minimalPoint;

    @Column(name = "average_point", nullable = false)
    @DecimalMin(value = 0, inclusive = false)
    private float averagePoint;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }
}

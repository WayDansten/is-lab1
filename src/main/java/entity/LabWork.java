package entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import entity.types.Difficulty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "labworks")
public class LabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1)
    private int id;

    @Column(name = "labwork_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "description", nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = true)
    private Difficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @Column(name = "minimal_point", nullable = true)
    private Double minimalPoint;

    @Column(name = "average_point", nullable = false)
    @DecimalMin(value = "0", inclusive = false)
    private float averagePoint;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }
}

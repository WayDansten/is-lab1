package dto.labwork;

import java.io.Serializable;
import java.time.LocalDateTime;

import dto.coordinates.CoordinatesResponseDTO;
import dto.discipline.DisciplineResponseDTO;
import dto.person.PersonResponseDTO;
import entity.types.Difficulty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabWorkResponseDTO implements Serializable {
    private int id;

    private String name;

    private CoordinatesResponseDTO coordinates;

    private LocalDateTime creationDate;

    private String description;

    private Difficulty difficulty;

    private DisciplineResponseDTO discipline;

    private Double minimalPoint;

    private float averagePoint;

    private PersonResponseDTO author;

}

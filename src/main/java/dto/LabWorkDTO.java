package dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabWorkDTO {
    private Integer id;
    private String name;
    private CoordinatesDTO coordinates;
    private LocalDateTime creationDate;
    private String description;
    private Difficulty difficulty;
    private Double minimalPoint;
    private Float averagePoint;
    private PersonDTO author;
}

package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import entity.types.Difficulty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabWorkDTO implements Serializable {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private CoordinatesDTO coordinates;

    @NotNull
    private LocalDateTime creationDate;

    private String description;

    private Difficulty difficulty;

    private DisciplineDTO discipline;

    private Double minimalPoint;

    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private Float averagePoint;
    
    private PersonDTO author;
}

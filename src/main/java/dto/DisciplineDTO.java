package dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisciplineDTO {
    private Integer id;
    
    @NotNull
    private String name;

    @NotNull
    @Min(value = 1)
    private Integer practiceHours;
}

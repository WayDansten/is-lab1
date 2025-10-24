package dto.discipline;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
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
public class DisciplineRequestDTO implements Serializable {
    private Integer id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Min(value = 1)
    private Integer practiceHours;
}

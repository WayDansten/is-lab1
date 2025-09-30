package dto.coordinates;

import java.io.Serializable;

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
public class CoordinatesRequestDTO implements Serializable{
    private Integer id;

    @NotNull
    private Float x;

    @NotNull
    @Min(value = -566)
    private Double y;
}

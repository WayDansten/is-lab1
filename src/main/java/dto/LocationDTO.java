package dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO implements Serializable {
    @NotNull
    @Size(max = 246)
    private String name;

    @NotNull
    private Long x;

    @NotNull
    private Double y;

    @NotNull
    private Float z;
}

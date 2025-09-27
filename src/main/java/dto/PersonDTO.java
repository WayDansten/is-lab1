package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import entity.types.Color;
import entity.types.Country;
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
public class PersonDTO implements Serializable {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private Color eyeColor;

    @NotNull
    private Color hairColor;

    private LocationDTO location;

    @NotNull
    private LocalDateTime birthday;

    @NotNull
    private Country nationality;
}

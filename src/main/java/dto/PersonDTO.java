package dto;

import java.time.LocalDateTime;

import entity.types.Color;
import entity.types.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Integer id;
    private String name;
    private Color eyeColor;
    private Color hairColor;
    private LocationDTO location;
    private LocalDateTime birthday;
    private Country nationality;
}

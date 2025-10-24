package dto.person;

import java.io.Serializable;
import java.time.LocalDateTime;

import dto.location.LocationResponseDTO;
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
public class PersonResponseDTO implements Serializable {
    private int id;

    private String name;

    private Color eyeColor;

    private Color hairColor;

    private LocationResponseDTO location;

    private LocalDateTime birthday;

    private Country nationality;

}

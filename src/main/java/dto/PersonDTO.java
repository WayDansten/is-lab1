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
public class PersonDTO {
    private Integer id;
    private String name;
    private Color eyeColor;
    private LocationDTO location;
    private LocalDateTime birthday;
    private Country nationality;
}

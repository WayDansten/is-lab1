package objects;

import objects.types.Color;
import objects.types.Country;
import objects.Location;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
    @Column(name="name", nullable=false, unique="true")
    @NotEmpty
    private String name;

    @Column(name="eye_color", nullable=true)
    private Color eyeColor;

    @Column(name="hair_color", nullable=false)
    private Color hairColor;

    @Column(name="location", nullable=true)
    private Location location;

    @Column(name="birthday", nullable=false)
    private java.time.LocalDateTime birthday;

    @Column(name="nationality", nullable=false)
    private Country nationality;
}

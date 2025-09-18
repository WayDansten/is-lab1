package objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="locations")
public class Location {
    @Column(name="name", nullable=false, unique=true, length=246)
    private String name;

    @Column(name="x", nullable=false)
    private Long x;

    @Column(name="y", nullable=false)
    private double y;

    @Column(name="z", nullable=false)
    private float z;
}

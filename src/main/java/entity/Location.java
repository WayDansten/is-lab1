package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "location_name", nullable = false, unique = true, length = 246)
    private String name;

    @Column(name = "x", nullable = false)
    private Long x;

    @Column(name = "y", nullable = false)
    private Double y;

    @Column(name = "z", nullable = false)
    private Float z;
}

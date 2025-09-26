package entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import entity.types.Color;
import entity.types.Country;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "person_name", nullable = false, unique = true)
    @NotEmpty
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "eye_color", nullable = true)
    private Color eyeColor;

    @Enumerated(EnumType.STRING)
    @Column(name = "hair_color", nullable = false)
    private Color hairColor;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "birthday", nullable = false)
    private LocalDateTime birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality", nullable = false)
    private Country nationality;
}

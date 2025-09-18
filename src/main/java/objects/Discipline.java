package objects;

import javax.persistence.Entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="disciplines")
public class Discipline {
    @Column(name="name", nullable=false, unique=true)
    @NotEmpty
    private String name; //Поле не может быть null, Строка не может быть пустой
    
    @Column(name="practiceHours")
    @Min(value=1)
    private int practiceHours;
}

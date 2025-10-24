package dto.discipline;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisciplineResponseDTO implements Serializable {
    private int id;

    private String name;

    private int practiceHours;

}

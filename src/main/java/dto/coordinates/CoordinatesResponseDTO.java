package dto.coordinates;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatesResponseDTO implements Serializable {
    private int id;

    private float x;

    private double y;
}

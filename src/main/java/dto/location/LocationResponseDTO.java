package dto.location;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponseDTO implements Serializable {
    private int id;

    private String name;

    private Long x;

    private double y;

    private float z;

}

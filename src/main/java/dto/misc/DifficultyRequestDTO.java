package dto.misc;

import java.io.Serializable;

import entity.types.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DifficultyRequestDTO implements Serializable {
    private Difficulty difficulty;
}
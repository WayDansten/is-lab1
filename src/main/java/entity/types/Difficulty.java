package entity.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Difficulty {
    VERY_EASY("Очень легко"),
    NORMAL("Нормально"),
    IMPOSSIBLE("Невозможно"),
    INSANE("Безумие");

    private String description;
}

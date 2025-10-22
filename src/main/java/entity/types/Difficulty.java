package entity.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Difficulty {
    VERY_EASY(1),
    NORMAL(2),
    INSANE(3),
    IMPOSSIBLE(4);

    private Integer value;

    public static Difficulty getByValue(Integer value) {
        for (Difficulty difficulty : values()) {
            if (difficulty.value == value) {
                return difficulty;
            }
        }
        return null;
    }
}

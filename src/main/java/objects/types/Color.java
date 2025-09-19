package objects.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Color {
    BLACK("Черный"),
    BLUE("Синий"),
    YELLOW("Желтый"),
    BROWN("Коричневый");

    private String name;
}

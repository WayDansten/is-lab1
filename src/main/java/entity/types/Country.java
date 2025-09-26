package entity.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Country {
    UNITED_KINGDOM("Великобритания"),
    USA("США"),
    FRANCE("Франция"),
    SOUTH_KOREA("Южная Корея"),
    NORTH_KOREA("Северная Корея");

    private String title;
}

package online.polp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Priority {
    RED(2),
    YELLOW(1),
    WHITE(0);

    private final int value;
}

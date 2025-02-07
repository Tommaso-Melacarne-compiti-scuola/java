package online.polp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CellType {
    EMPTY(" "), CROSS("X"), CIRCLE("O");

    private final String symbol;

    @Override
    public String toString() {
        return symbol;
    }
}

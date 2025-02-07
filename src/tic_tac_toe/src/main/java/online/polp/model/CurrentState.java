package online.polp.model;

import lombok.Data;

@Data
public class CurrentState {
    private final CellType nextPlayer;
    private final Grid grid;
}

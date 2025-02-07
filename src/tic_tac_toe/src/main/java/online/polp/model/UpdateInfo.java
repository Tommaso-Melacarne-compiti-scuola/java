package online.polp.model;

import lombok.Data;

@Data
public class UpdateInfo {
    private final CellType nextPlayer;
    private final Grid grid;
}

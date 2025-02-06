package online.polp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid extends ArrayList<List<CellType>> {
    public Grid() {
        for (int i = 0; i < 3; i++) {
            this.add(Arrays.asList(CellType.EMPTY, CellType.EMPTY, CellType.EMPTY));
        }
    }

    public CellType getRowAndColumn(int row, int col) {
        return get(row).get(col);
    }

    public void setRowAndColumn(int row, int col, CellType cellType) {
        get(row).set(col, cellType);
    }
}

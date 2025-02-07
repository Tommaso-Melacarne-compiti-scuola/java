package online.polp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid extends ArrayList<List<CellType>> {
    public Grid() {
        final int SIZE = 3;

        for (int i = 0; i < SIZE; i++) {
            this.add(Arrays.asList(CellType.EMPTY, CellType.EMPTY, CellType.EMPTY));
        }
    }

    public CellType getRowAndColumn(int row, int col) {
        return get(row).get(col);
    }

    public void setRowAndColumn(int row, int col, CellType cellType) {
        get(row).set(col, cellType);
    }

    public boolean isFull() {
        for (List<CellType> row : this) {
            for (CellType cell : row) {
                if (cell == CellType.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWinner() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private boolean checkRows() {
        for (List<CellType> row : this) {
            for (int i = 1; i < row.size(); i++) {
                if (row.get(i) != row.get(0)) {
                    break;
                }
                if (i == row.size() - 1) {
                    return row.get(0) != CellType.EMPTY;
                }
            }
        }

        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < size(); i++) {
            for (int j = 1; j < size(); j++) {
                if (get(j).get(i) != get(0).get(i)) {
                    break;
                }
                if (j == size() - 1) {
                    return get(0).get(i) != CellType.EMPTY;
                }
            }
        }

        return false;
    }

    private boolean checkDiagonals() {
        for (int i = 1; i < size(); i++) {
            if (get(i).get(i) != get(0).get(0)) {
                break;
            }
            if (i == size() - 1) {
                return get(0).get(0) != CellType.EMPTY;
            }
        }

        for (int i = 1; i < size(); i++) {
            if (get(i).get(size() - i - 1) != get(0).get(size() - 1)) {
                break;
            }
            if (i == size() - 1) {
                return get(0).get(size() - 1) != CellType.EMPTY;
            }
        }

        return false;
    }
}

package online.polp.model;

import lombok.Getter;

public class TicTacToeModel {
    @Getter
    private static final Grid grid = new Grid();
    private static int playerClicking = 0;

    public static void makePlay(int row, int col) {
        if (grid.getRowAndColumn(row, col) != CellType.EMPTY) {
            return;
        }

        CellType cellType = playerClicking % 2 == 0 ? CellType.CROSS : CellType.CIRCLE;

        grid.setRowAndColumn(row, col, cellType);

        playerClicking++;
    }

    public static UpdateInfo getUpdateInfo() {
        return new UpdateInfo(playerClicking % 2, grid);
    }
}

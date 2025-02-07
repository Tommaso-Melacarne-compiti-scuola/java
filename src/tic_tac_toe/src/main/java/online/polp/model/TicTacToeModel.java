package online.polp.model;

import lombok.Getter;

public class TicTacToeModel {
    @Getter
    private static final Grid grid = new Grid();
    private static int turn = 0;

    public static void makePlay(int row, int col) {
        if (grid.getRowAndColumn(row, col) != CellType.EMPTY) {
            return;
        }

        CellType cellType = turn % 2 == 0 ? CellType.CROSS : CellType.CIRCLE;

        grid.setRowAndColumn(row, col, cellType);

        turn++;
    }

    public static CurrentState getCurrentState() {
        return new CurrentState(turn % 2 == 0 ? CellType.CROSS : CellType.CIRCLE, grid);
    }

    public static GameStatus getGameStatus() {
        if (grid.hasWinner()) {
            return turn % 2 == 1 ? GameStatus.X_WINS : GameStatus.O_WINS;
        }

        if (grid.isFull()) {
            return GameStatus.DRAW;
        }

        return GameStatus.RUNNING;
    }
}

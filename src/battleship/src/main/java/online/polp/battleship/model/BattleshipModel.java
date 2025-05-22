package online.polp.battleship.model;

import lombok.Getter;
import lombok.Setter;

public class BattleshipModel {
    @Getter
    @Setter
    private static Player player;
    @Getter
    @Setter
    private static Player computer;

    public static void resetGame() {
        player.getBoard().getHits().clear();
        player.getBoard().getShips().clear();
        computer.getBoard().getHits().clear();
        computer.getBoard().getShips().clear();
    }

    public static boolean hasStarted() {
        if (player == null || computer == null) {
            return false;
        }

        return !player.getBoard().getShips().isEmpty() && !computer.getBoard().getShips().isEmpty();
    }

    public static int getCurrentPlayerTurn() {
        return player.getBoard().getHits().size();
    }
}

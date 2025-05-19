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
}

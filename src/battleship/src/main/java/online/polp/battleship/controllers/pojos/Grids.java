package online.polp.battleship.controllers.pojos;

import lombok.Data;
import online.polp.battleship.model.Player;

import java.util.List;

@Data
public class Grids {
    private final List<PointShip> player;
    private final List<PointShip> computer;

    public Grids(Player player, Player computer) {
        this.player = fromBoardToList(player);
        this.computer = fromBoardToList(computer);
    }

    private List<PointShip> fromBoardToList(Player player) {
        // Takes ship array and converts it to a PointShip array
        return player.getBoard()
                     .getShips()
                     .stream()
                     .map(PointShip::new)
                     .toList();
    }
}

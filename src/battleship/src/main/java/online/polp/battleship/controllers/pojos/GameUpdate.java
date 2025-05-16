package online.polp.battleship.controllers.pojos;

import lombok.Data;
import online.polp.battleship.model.Hit;
import online.polp.battleship.model.Player;

import java.util.List;

@Data
public class GameUpdate {
    private final List<PointShip> playerShips;
    private final List<Hit> playerHits;
    private final List<Hit> computerHits;


    public GameUpdate(Player player, Player computer) {
        this.playerShips = createPointShipFromPlayer(player);
        this.playerHits = player.getBoard().getHits();
        this.computerHits = computer.getBoard().getHits();
    }

    /**
     * Takes player and converts it to a PointShip array
     * @param player The player to convert
     * @return The PointShip array
     */
    private List<PointShip> createPointShipFromPlayer(Player player) {
        return player.getBoard()
                     .getShips()
                     .stream()
                     .map(PointShip::new)
                     .toList();
    }
}

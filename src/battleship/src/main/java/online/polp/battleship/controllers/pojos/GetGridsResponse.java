package online.polp.battleship.controllers.pojos;

import lombok.Data;
import online.polp.battleship.constants.BoardConstants;
import online.polp.battleship.model.Player;

import java.util.List;

@Data
public class GetGridsResponse {
    private final List<Integer> player;
    private final List<Integer> computer;

    public GetGridsResponse(Player player, Player computer) {
        this.player = fromBoardToList(player);
        this.computer = fromBoardToList(computer);
    }

    private List<Integer> fromBoardToList(Player player) {
        // Takes a single ship each time and maps it to a list of points, then merges the lists of points
        return player.getBoard()
                     .getShips()
                     .stream()
                     .map(ship -> ship
                         .getPoints()
                         .stream()
                         .map(point -> point.getX() + point.getY() * BoardConstants.BOARD_SIZE)
                         .toList())
                     .flatMap(List::stream)
                     .toList();
    }
}

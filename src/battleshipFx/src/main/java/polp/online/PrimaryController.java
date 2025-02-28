package polp.online;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import polp.online.model.*;

import java.util.List;
import java.util.stream.Stream;

public class PrimaryController {
    @FXML
    private HBox mainHbox;

    private final Player humanPlayer = new Player("Human");
    private final Player pcPlayer = new Player("PC");

    public PrimaryController() {
        // TODO: Add ships to the boards manually for human player
        humanPlayer.getBoard().addRandomShips();

        pcPlayer.getBoard().addRandomShips();

        List<Player> players = List.of(humanPlayer, pcPlayer);

        for (Player player : players) {
            BattleshipModel.addPlayer(player);
        }
    }

    @FXML
    private void initialize() {
        List<Integer> playerIds = Stream.of(humanPlayer, pcPlayer).map(Player::getId).toList();

        renderBoard(playerIds, false);
    }

    private void renderBoard(List<Integer> playerIds, boolean gameDidNotStart) {
        mainHbox.getChildren().clear();

        for (int playerId : playerIds) {
            Board board = BattleshipModel.getPlayerById(playerId).getBoard();
            List<Hit> hits = board.getHits();
            List<Ship> ships = board.getShips();

            CustomGridPane boardGrid = new CustomGridPane(gameDidNotStart);
            boardGrid.fill(hits, ships);
            mainHbox.getChildren().add(boardGrid);
        }
    }
}

package polp.online;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import polp.online.model.*;

import java.util.List;

public class PrimaryController {
    @FXML
    private HBox mainHbox;

    private int humanId;
    private int pcId;

    @FXML
    private void initialize() {
        Player humanPlayer = new Player("Human");
        Player pcPlayer = new Player("PC");

        BattleshipModel.addPlayer(humanPlayer);
        BattleshipModel.addPlayer(pcPlayer);

        humanId = humanPlayer.getId();
        pcId = pcPlayer.getId();

        // TODO: Add ships to the boards manually for player 1
        BattleshipModel.getPlayerById(humanId).getBoard().addRandomShips();

        BattleshipModel.getPlayerById(pcId).getBoard().addRandomShips();

        List<Integer> playerIds = List.of(humanId, pcId);

        renderBoard(playerIds, true);
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

package polp.online;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
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

    private void renderBoard(List<Integer> playerIds, boolean firstRender) {
        mainHbox.getChildren().clear();

        for (int playerId : playerIds) {
            Board board = BattleshipModel.getPlayerById(playerId).getBoard();
            List<Hit> hits = board.getHits();
            List<Ship> ships = board.getShips();

            GridPane boardGrid = createBoard(firstRender);
            fillBoard(boardGrid, hits, ships);
            mainHbox.getChildren().add(boardGrid);
        }
    }

    private void fillBoard(GridPane board, List<Hit> hits, List<Ship> ships) {
        for (Ship ship : ships) {
            for (Point point : ship.getPoints()) {
                Button button = (Button) board.getChildren().get(point.getX() + point.getY() * 10);
                button.getStyleClass().add("ship-button");
            }
        }


        for (Hit hit : hits) {
            Button button = (Button) board.getChildren().get(hit.getX() + hit.getY() * 10);

            String classToInject = switch (hit.getResult()) {
                case HIT -> "hit-button";
                case SUNK -> "sunk-button";
                case MISS -> "miss-button";
            };

            button.getStyleClass().add(classToInject);
        }
    }

    private GridPane createBoard(boolean disabled) {
        GridPane board = new GridPane();
        board.setPrefSize(500, 500);
        board.setGridLinesVisible(true);

        board.getColumnConstraints().add(new ColumnConstraints(50));
        board.getRowConstraints().add(new RowConstraints(50));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                button.getStyleClass().add("board-button");

                button.setDisable(disabled);

                board.add(button, i, j);
            }
        }

        return board;
    }
}

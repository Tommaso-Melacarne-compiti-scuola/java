package polp.online;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import polp.online.model.BattleshipModel;
import polp.online.model.Player;

import java.util.List;

public class PrimaryController {
    @FXML
    private HBox mainHbox;

    private int player1Id;
    private int player2Id;

    @FXML
    private void initialize() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        BattleshipModel.addPlayer(player1);
        BattleshipModel.addPlayer(player2);

        player1Id = player1.getId();
        player2Id = player2.getId();

        renderBoard(player1Id, player2Id, true);
    }

    private void renderBoard(int player1Id, int player2Id, boolean firstRender) {
        List<Hit> player1Hits = BattleshipModel.getPlayerById(player1Id).getBoard().getHits();
        List<Hit> player2Hits = BattleshipModel.getPlayerById(player2Id).getBoard().getHits();

        mainHbox.getChildren().clear();

        GridPane playerBoard = createBoard(firstRender);
        fillBoard(playerBoard, player1Hits);
        mainHbox.getChildren().add(playerBoard);

        GridPane pcBoard = createBoard(!firstRender);
        fillBoard(pcBoard, player2Hits);
        mainHbox.getChildren().add(pcBoard);
    }

    private void fillBoard(GridPane board, List<Hit> hits) {
        for (Hit hit : hits) {
            Button button = (Button) board.getChildren().get(hit.getX() + hit.getY() * 10);

            String classToInject = switch (hit.getResult()) {
                case HIT -> "hit";
                case SUNK -> "sunk";
                case MISS -> "miss";
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

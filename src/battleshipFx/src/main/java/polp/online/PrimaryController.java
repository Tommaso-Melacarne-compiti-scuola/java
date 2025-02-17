package polp.online;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import polp.online.model.BattleshipModel;
import polp.online.model.Player;

public class PrimaryController {
    @FXML
    private StackPane mainStackPane;

    @FXML
    private void initialize() {
        BattleshipModel.addPlayer(new Player("Player 1"));
        BattleshipModel.addPlayer(new Player("Player 2"));

        GridPane board = createBoard();

        mainStackPane.getChildren().add(board);

    }

    private GridPane createBoard() {
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

                board.add(button, i, j);
            }
        }

        return board;
    }
}

package online.polp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import online.polp.model.CellType;
import online.polp.model.TicTacToeModel;
import online.polp.model.UpdateInfo;

public class PrimaryController {
    @FXML
    GridPane gridPane;
    @FXML
    Text turnIndicator;

    @SuppressWarnings("unused")
    public void initialize() {
        // Get the number of rows and columns of the grid pane
        int numRows = gridPane.getRowConstraints().size();
        int numCols = gridPane.getColumnConstraints().size();

        // Create a pane inside each cell of the grid pane
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Button button = new Button();
                button.getStyleClass().add("field-button");

                // Set the handler for the click event
                int finalI = i;
                int finalJ = j;
                button.setOnMouseClicked(event -> handleClick(finalI, finalJ));

                gridPane.add(button, j, i);
            }
        }
    }

    private void handleClick(int row, int col) {
        TicTacToeModel.makePlay(row, col);
        rerenderUpdate(TicTacToeModel.getUpdateInfo());
    }

    private void rerenderUpdate(UpdateInfo updateInfo) {
        // Get the number of rows and columns of the grid pane
        int numRows = gridPane.getRowConstraints().size();
        int numCols = gridPane.getColumnConstraints().size();

        // Iterate over the grid pane to update the content of each cell
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Button button = (Button) gridPane.getChildren().get(i * numCols + j + 1);
                CellType rowAndColumn = updateInfo.getGrid().getRowAndColumn(i, j);

                button.setText(rowAndColumn.toString());
            }
        }

        turnIndicator.setText("It's " + updateInfo.getNextPlayer() + "'s turn");
    }
}

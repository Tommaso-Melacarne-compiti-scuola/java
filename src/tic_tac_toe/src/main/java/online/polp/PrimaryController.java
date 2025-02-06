package online.polp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import online.polp.model.CellType;
import online.polp.model.TicTacToeModel;
import online.polp.model.UpdateInfo;

public class PrimaryController {
    @FXML
    GridPane gridPane;

    @SuppressWarnings("unused")
    public void initialize() {
        // Get the number of rows and columns of the grid pane
        int numRows = gridPane.getRowConstraints().size();
        int numCols = gridPane.getColumnConstraints().size();

        // Create a pane inside each cell of the grid pane
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Pane pane = new Pane();
                // Center items both vertically and horizontally
                pane.setStyle("-fx-alignment: center;");

                // Set the handler for the click event
                int finalI = i;
                int finalJ = j;
                pane.setOnMouseClicked(event -> handleClick(finalI, finalJ));

                gridPane.add(pane, j, i);
            }
        }
    }

    private void handleClick(int row, int col) {
        System.out.printf("Clicked on cell (%d, %d)%n", row, col);
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
                Pane pane = (Pane) gridPane.getChildren().get(i * numCols + j + 1);
                CellType rowAndColumn = updateInfo.getGrid().getRowAndColumn(i, j);

                ObservableList<Node> paneChildren = pane.getChildren();

                String style = "-fx-font-size: 40; -fx-font-weight: bold; -fx-alignment: center;";

                switch (rowAndColumn) {
                    case CROSS: {
                        Text text = new Text("X");
                        text.setStyle(style);
                        paneChildren.add(text);
                        break;
                    }
                    case CIRCLE: {
                        Text text = new Text("O");
                        text.setStyle(style);
                        paneChildren.add(text);
                        break;
                    }
                    case EMPTY:
                        paneChildren.clear();
                        break;
                }
            }
        }
    }
}

package polp.online;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import polp.online.model.Hit;
import polp.online.model.Point;
import polp.online.model.Ship;

import java.util.List;

class CustomGridPane extends GridPane {
    CustomGridPane(boolean disabled) {
        super();

        setPrefSize(500, 500);
        setGridLinesVisible(true);

        getColumnConstraints().add(new ColumnConstraints(50));
        getRowConstraints().add(new RowConstraints(50));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button button = new Button();
                button.setPrefSize(50, 50);
                button.getStyleClass().add("board-button");

                button.setDisable(disabled);

                add(button, i, j);
            }
        }
    }

    void fill(List<Hit> hits, List<Ship> ships) {
        for (Ship ship : ships) {
            for (Point point : ship.getPoints()) {
                Button button = (Button) this.getChildren().get(point.getX() + point.getY() * 10);
                button.getStyleClass().add("ship-button");
            }
        }


        for (Hit hit : hits) {
            Button button = (Button) this.getChildren().get(hit.getX() + hit.getY() * 10);

            String classToInject = switch (hit.getResult()) {
                case HIT -> "hit-button";
                case SUNK -> "sunk-button";
                case MISS -> "miss-button";
            };

            button.getStyleClass().add(classToInject);
        }
    }
}

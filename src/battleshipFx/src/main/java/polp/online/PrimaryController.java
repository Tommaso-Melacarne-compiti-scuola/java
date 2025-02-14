package polp.online;

import java.io.IOException;
import javafx.fxml.FXML;
import polp.online.model.BattleshipModel;
import polp.online.model.Board;
import polp.online.model.Player;

public class PrimaryController {
    private void initialize() {
        BattleshipModel.addPlayer(new Player("Player 1"));
        BattleshipModel.addPlayer(new Player("Player 2"));
    }
}

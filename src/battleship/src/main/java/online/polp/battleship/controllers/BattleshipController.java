package online.polp.battleship.controllers;

import jakarta.annotation.PostConstruct;
import online.polp.battleship.controllers.pojos.NewGameResponse;
import online.polp.battleship.model.BattleshipModel;
import online.polp.battleship.model.Player;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342") // Allow requests from any origin
class BattleshipController {
    @PostConstruct
    public void onStartup() {
        Player humanPlayer = new Player("Human");
        Player pcPlayer = new Player("PC");

        // TODO: Allow human player to add ships to the board
        humanPlayer.getBoard().addRandomShips();

        pcPlayer.getBoard().addRandomShips();

        List<Player> players = List.of(humanPlayer, pcPlayer);

        for (Player player : players) {
            BattleshipModel.addPlayer(player);
        }
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from Battleship!";
    }

    @GetMapping("/new-game")
    public NewGameResponse getNewGame() {
        List<Player> players =  BattleshipModel.getPlayers();

        return new NewGameResponse(players);
    }
}

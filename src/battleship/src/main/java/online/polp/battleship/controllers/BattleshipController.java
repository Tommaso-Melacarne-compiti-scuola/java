package online.polp.battleship.controllers;

import jakarta.annotation.PostConstruct;
import online.polp.battleship.controllers.pojos.GetGridsResponse;
import online.polp.battleship.controllers.pojos.NewGameRequest;
import online.polp.battleship.exceptions.ShipAddException;
import online.polp.battleship.model.BattleshipModel;
import online.polp.battleship.model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:63342") // Allow requests from any origin
class BattleshipController {
    @PostConstruct
    public void onStartup() {
        Player computer = new Player("Computer");

        computer.getBoard().addRandomShips();

        BattleshipModel.setComputer(computer);
    }

    /**
     * Get Grids
     *
     * @return The current game grids
     */
    @GetMapping("/get-grids")
    public GetGridsResponse getGrids() {
        return new GetGridsResponse(
            BattleshipModel.getPlayer(),
            BattleshipModel.getComputer()
        );
    }

    /**
     * New Game
     * Creates a new game with the given player ships.
     *
     * @param newGameRequest A request optionally indicating player's ships
     * @return The grid of the new game
     */
    @PostMapping("/new-game")
    public GetGridsResponse getNewGame(@RequestBody(required = false) NewGameRequest newGameRequest) throws ShipAddException {
        Player humanPlayer = new Player("Player");

        if (newGameRequest == null) {
            humanPlayer.getBoard().addRandomShips();
        } else {
            humanPlayer.getBoard().addShips(newGameRequest.getShips());
        }

        BattleshipModel.setPlayer(humanPlayer);

        return new GetGridsResponse(
            BattleshipModel.getPlayer(),
            BattleshipModel.getComputer()
        );
    }

    @PutMapping("/attack/{index}")
    public void attack(@PathVariable int index) {
    }
}

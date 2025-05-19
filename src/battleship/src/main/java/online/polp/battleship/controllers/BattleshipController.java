package online.polp.battleship.controllers;

import jakarta.annotation.PostConstruct;
import online.polp.battleship.constants.BoardConstants;
import online.polp.battleship.controllers.pojos.AttackResponse;
import online.polp.battleship.controllers.pojos.NewGameRequest;
import online.polp.battleship.controllers.pojos.GameUpdate;
import online.polp.battleship.exceptions.ShipAddException;
import online.polp.battleship.model.AttackResult;
import online.polp.battleship.model.BattleshipModel;
import online.polp.battleship.model.Player;
import online.polp.battleship.model.Point;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
class BattleshipController {
    /**
     * Get Update
     *
     * @return The current game status
     */
    @GetMapping("/get-update")
    public GameUpdate getUpdate() {
        return new GameUpdate(
            BattleshipModel.getPlayer(),
            BattleshipModel.getComputer()
        );
    }

    @GetMapping("/has-started")
    public boolean hasStarted() {
        return BattleshipModel.getPlayer() != null && BattleshipModel.getComputer() != null;
    }

    @GetMapping("/reset-game")
    public GameUpdate resetGame() {
        BattleshipModel.resetGame();

        return new GameUpdate(
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
    public GameUpdate startNewGame(@RequestBody(required = false) NewGameRequest newGameRequest) throws ShipAddException {
        BattleshipModel.setComputer(new Player("Computer"));
        BattleshipModel.setPlayer(new Player("Player"));

        BattleshipModel.getComputer().getBoard().addRandomShips();

        if (newGameRequest == null) {
            BattleshipModel.getPlayer().getBoard().addRandomShips();
        } else {
            BattleshipModel.getPlayer().getBoard().addShips(newGameRequest.getShips());
        }

        return new GameUpdate(
            BattleshipModel.getPlayer(),
            BattleshipModel.getComputer()
        );
    }

    @PutMapping("/attack/{index}")
    public AttackResponse attack(@PathVariable int index) {
        AttackResult playerAttackResult = BattleshipModel
            .getComputer()
            .getBoard()
            .attack(Point.fromIndex(index, BoardConstants.BOARD_SIZE));
        AttackResult computerAttackResult = BattleshipModel.getPlayer().getBoard().attack(
            Point.randomPoint(BoardConstants.BOARD_SIZE)
        );

        return new AttackResponse(
            playerAttackResult,
            computerAttackResult,
            new GameUpdate(
                BattleshipModel.getPlayer(),
                BattleshipModel.getComputer()
            )
        );
    }
}

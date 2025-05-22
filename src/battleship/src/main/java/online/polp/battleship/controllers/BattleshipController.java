package online.polp.battleship.controllers;

import online.polp.battleship.constants.BoardConstants;
import online.polp.battleship.controllers.pojos.AttackResponse;
import online.polp.battleship.controllers.pojos.GameUpdate;
import online.polp.battleship.controllers.pojos.HasStartedResponse;
import online.polp.battleship.controllers.pojos.NewGameRequest;
import online.polp.battleship.exceptions.ShipAddException;
import online.polp.battleship.model.BattleshipModel;
import online.polp.battleship.model.Hit;
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
    public HasStartedResponse hasStarted() {
        return new HasStartedResponse(BattleshipModel.hasStarted());
    }

    @DeleteMapping("/reset-game")
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
        Hit playerAttackResult = BattleshipModel
            .getComputer()
            .attack(Point.fromIndex(index, BoardConstants.BOARD_SIZE));

        Hit computerAttackResult;

        while (true) {
            Point randomPoint = Point.randomPoint(BoardConstants.BOARD_SIZE);

            // Check if the point is already attacked
            boolean wasAttackAlreadyMade = BattleshipModel
                .getPlayer()
                .getBoard()
                .getHits()
                .stream()
                .anyMatch((hit) -> hit
                    .getX()
                    .equals(randomPoint.getX()) && hit.getY().equals(randomPoint.getY()));

            if (wasAttackAlreadyMade) {
                continue;
            }

            computerAttackResult = BattleshipModel.getPlayer().attack(
                randomPoint
            );
            break;
        }


        int currentTurn = BattleshipModel.getCurrentPlayerTurn();

        return new AttackResponse(
            currentTurn,
            playerAttackResult,
            computerAttackResult,
            new GameUpdate(
                BattleshipModel.getPlayer(),
                BattleshipModel.getComputer()
            )
        );
    }
}

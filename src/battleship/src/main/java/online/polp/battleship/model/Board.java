package online.polp.battleship.model;

import lombok.Getter;
import online.polp.battleship.constants.BoardConstants;
import online.polp.battleship.exceptions.ShipAddException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Board {
    private final List<Ship> ships = new ArrayList<>();
    private final List<Hit> hits = new ArrayList<>();

    public void addShip(Ship ship) throws ShipAddException {
        if (!ship.isInsideBoard(BoardConstants.BOARD_SIZE)) {
            throw new ShipAddException("Invalid ship: outside the board boundaries");
        }

        for (Ship s : ships) {
            if (ship.collidesWith(s)) {
                throw new ShipAddException("Invalid ship: collides with another ship");
            }
        }

        ships.add(ship);
    }

    /**
     * Attacks this player's board at the given point
     *
     * @param point The point to attack
     * @return The result of the attack
     */
    public AttackResult attack(Point point) {
        for (Ship ship : ships) {
            if (ship.isHit(point)) {
                Hit hit = new Hit(AttackResult.HIT, point);

                hits.add(hit);

                if (ship.isSunk(hits)) {
                    hit.setResult(AttackResult.SUNK);
                }

                return hit.getResult();
            }
        }

        return AttackResult.MISS;
    }

    public void addRandomShip(int length) {
        int boardSize = BoardConstants.BOARD_SIZE;

        while (true) {
            Ship ship = Ship
                .builder()
                .position(Point.randomPoint(boardSize))
                .length(length)
                .orientation(Orientation.randomOrientation())
                .build();

            try {
                addShip(ship);
                break;
            } catch (ShipAddException e) {
                // New random ship
            }
        }
    }

    public void addRandomShips() {
        for (int shipLength : BoardConstants.SHIP_LENGTHS) {
            addRandomShip(shipLength);
        }
    }

    public void addShips(List<Ship> ships) throws ShipAddException {
        for (Ship ship : ships) {
            addShip(ship);
        }
    }
}

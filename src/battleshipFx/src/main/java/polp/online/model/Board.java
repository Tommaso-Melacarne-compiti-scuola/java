package polp.online.model;

import polp.online.Hit;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Ship> ships = new ArrayList<>();
    private final List<Hit> hits = new ArrayList<>();

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public AttackResult attack(Point point) {
        for (Ship ship : ships) {
            if (ship.isHit(point)) {
                AttackResult result = AttackResult.HIT;

                if (ship.isSunk(hits)) {
                    return AttackResult.SUNK;
                }

                hits.add(new Hit(result, point));
                return result;
            }
        }

        return AttackResult.MISS;
    }
}

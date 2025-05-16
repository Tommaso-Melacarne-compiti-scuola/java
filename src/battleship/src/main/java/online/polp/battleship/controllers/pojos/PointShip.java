package online.polp.battleship.controllers.pojos;

import lombok.Data;
import online.polp.battleship.model.Point;
import online.polp.battleship.model.Ship;

import java.util.List;

@Data
public class PointShip {
    private final List<Point> points;

    public PointShip(Ship ship) {
        this.points = ship.getPoints();
    }
}

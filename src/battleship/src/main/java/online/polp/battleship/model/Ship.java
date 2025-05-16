package online.polp.battleship.model;

import lombok.Builder;
import lombok.Data;
import online.polp.battleship.utils.Utils;

import java.util.List;

@Data
@Builder
public class Ship {
    private final Point position;
    private final int length;
    private final Orientation orientation;
    private final String color = Utils.getRandomHexColor();

    public boolean isHit(Point point) {
        Point end = position.translateInDirection(orientation, length - 1);

        return point.isInsideRectangle(position, end);
    }

    public boolean isInsideBoard(int boardSize) {
        if (!position.isInsideBoard(boardSize, boardSize)) {
            return false;
        }

        Point end = position.translateInDirection(orientation, length - 1);

        return end.isInsideBoard(boardSize, boardSize);
    }

    public boolean isSunk(List<Hit> hits) {
        List<Point> shipPoints = computePoints();

        for (Point point : shipPoints) {
            boolean pointIsHit = false;

            for (Hit hit : hits) {
                // Compare coordinates directly since Hit extends Point
                if (hit.getX().equals(point.getX()) && hit.getY().equals(point.getY())) {
                    pointIsHit = true;
                    break;
                }
            }

            if (!pointIsHit) {
                return false; // Found a point that hasn't been hit
            }
        }

        return true; // All points have been hit
    }

    /**
     * Check if this ship collides with another ship
     *
     * @param other The other ship
     * @return true if the ships collide, false otherwise
     */
    public boolean collidesWith(Ship other) {
        Point end = position.translateInDirection(orientation, length - 1);

        return position.isInsideRectangle(
            other.position,
            other.position.translateInDirection(
                other.orientation,
                other.length - 1
            )
        ) ||
            other.position.isInsideRectangle(position, end);
    }

    public List<Point> computePoints() {
        return position.getPointsInDirection(orientation, length);
    }
}

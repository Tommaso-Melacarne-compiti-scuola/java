package online.polp.battleship.model;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class Ship {
    private final Point position;
    private final int length;
    private final Orientation orientation;

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
        // Get all points occupied by this ship
        List<Point> thisPoints = this.computePoints();
        
        // Get all points occupied by the other ship
        List<Point> otherPoints = other.computePoints();
        
        // Check if any point of this ship is in the same position as any point of the other ship
        for (Point thisPoint : thisPoints) {
            for (Point otherPoint : otherPoints) {
                if (thisPoint.getX().equals(otherPoint.getX()) && 
                    thisPoint.getY().equals(otherPoint.getY())) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public List<Point> computePoints() {
        return position.getPointsInDirection(orientation, length);
    }
}

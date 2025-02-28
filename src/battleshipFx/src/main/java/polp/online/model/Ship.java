package polp.online.model;

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
        Point end = position.extendInDirection(orientation, length - 1);

        return point.isInsideRectangle(position, end);
    }

    public boolean isInsideBoard(int boardSize) {
        if (!position.isInsideBoard(boardSize, boardSize)) {
            return false;
        }

        Point end = position.extendInDirection(orientation, length - 1);

        return end.isInsideBoard(boardSize, boardSize);
    }

    public boolean isSunk(List<Hit> hits) {
        for (int i = 0; i < length; i++) {
            Point point = position.extendInDirection(orientation, i);

            if (hits.stream().noneMatch(hit -> hit.equals(point))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Check if this ship collides with another ship
     *
     * @param other The other ship
     * @return true if the ships collide, false otherwise
     */
    public boolean collidesWith(Ship other) {
        Point end = position.extendInDirection(orientation, length - 1);

        return position.isInsideRectangle(other.position, other.position.extendInDirection(other.orientation, other.length - 1)) ||
               other.position.isInsideRectangle(position, end);
    }

    public List<Point> getPoints() {
        return position.getPointsInDirection(orientation, length);
    }
}

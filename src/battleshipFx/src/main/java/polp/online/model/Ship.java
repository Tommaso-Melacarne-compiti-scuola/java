package polp.online.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import polp.online.Hit;

import java.util.List;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Ship {
    private final Point position;
    private final int length;
    private final Orientation orientation;

    public Ship(Point start, int length, Orientation orientation, int boardSize) {
        this(start, length, orientation);

        if (!isInsideBoard(boardSize)) {
            throw new IllegalArgumentException("Invalid ship: outside the board boundaries");
        }
    }

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
}

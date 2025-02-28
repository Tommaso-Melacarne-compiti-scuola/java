package polp.online.model;

import lombok.*;
import polp.online.singletons.RandomSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Point {
    @NonNull
    private int x;
    @NonNull
    private int y;

    public Point(Point other) {
        this(other.x, other.y);
    }

    /**
     * Tests if this point is inside a rectangle
     *
     * @param start The start of the rectangle
     * @param end   The end of the rectangle
     * @return True if the point is inside the rectangle
     */
    public boolean isInsideRectangle(Point start, Point end) {
        return x >= start.x && x <= end.x && y >= start.y && y <= end.y;
    }

    /**
     * Tests if this point is in a grid
     *
     * @param width  The width of the grid
     * @param height The height of the grid
     * @return True if the point is in the grid
     */
    public boolean isInsideBoard(int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Point extendInDirection(Orientation orientation, int length) {
        return extend(
            orientation == Orientation.HORIZONTAL ? length : 0,
            orientation == Orientation.VERTICAL ? length : 0
        );
    }

    public Point extend(int dx, int dy) {
        return new Point(x + dx, y + dy);
    }

    public Point add(Point point) {
        return new Point(x + point.x, y + point.y);
    }

    public static Point randomPoint(int maxX, int maxY) {
        Random random = RandomSingleton.getInstance();

        return new Point(random.nextInt(maxX), random.nextInt(maxY));
    }

    public static Point randomPoint(int maxXY) {
        return randomPoint(maxXY, maxXY);
    }

    public List<Point> getPointsInDirection(Orientation orientation, int length) {
        List<Point> points = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            points.add(extendInDirection(orientation, i));
        }

        return points;
    }
}

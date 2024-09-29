package online.polp;

import java.util.Arrays;

public class Square extends Polygon {
    public Square(Point[] points) {
        super(points);
        if (points.length != 4) {
            throw new IllegalArgumentException("A square must have exactly 4 points");
        }
    }

    public Square(Square other) {
        super(other.points);
    }

    @Override
    public double calculatePerimeter() {
        return 4 * points[0].calculateDistance(points[1]);
    }

    @Override
    public double calculateArea() {
        return points[0].calculateDistance(points[1]) * points[0].calculateDistance(points[1]);
    }

    @Override
    public String toString() {
        return "Square{" +
                "points=" + Arrays.toString(points) +
                '}';
    }
}

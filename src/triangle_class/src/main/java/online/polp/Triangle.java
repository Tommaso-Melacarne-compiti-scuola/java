package online.polp;

import java.util.Arrays;

public class Triangle extends Polygon {
    public Triangle(Point[] points) {
        super(points);
        if (points.length != 3) {
            throw new IllegalArgumentException("A triangle must have exactly 3 points");
        }
    }

    public Triangle(Triangle other) {
        // Directly call the constructor of the superclass,
        // because it is guaranteed to have 3 points
        super(other.points);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "points=" + Arrays.toString(points) +
                '}';
    }
}

package online.polp;

import java.util.Arrays;

public class Polygon {
    public Point[] points;

    public Polygon(Point[] points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "points=" + Arrays.toString(points) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Polygon polygon = (Polygon) o;
        return Arrays.equals(points, polygon.points);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(points);
    }

    public double calculatePerimeter() {
        double perimeter = 0;
        for (int i = 0; i < points.length - 1; i++) {
            perimeter += points[i].calculateDistance(points[i + 1]);
        }
        perimeter += points[points.length - 1].calculateDistance(points[0]);
        return perimeter;
    }

    // Calculate area using the Gauss's area formula
    public double calculateArea() {
        double area = 0;

        for (int i = 0; i < points.length - 1; i++) {
            area += points[i].getX() * points[i + 1].getY();
            area -= points[i + 1].getX() * points[i].getY();
        }

        area += points[points.length - 1].getX() * points[0].getY();
        area -= points[0].getX() * points[points.length - 1].getY();

        return Math.abs(area) / 2.0;
    }
}

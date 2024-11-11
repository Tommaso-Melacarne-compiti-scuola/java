package online.polp.shapes;

import online.polp.colorize.Color;

public abstract class Shape implements Resizable {
    private final Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract double getArea();

    public abstract String getType();

    @Override
    public String toString() {
        return String.format("Shape: %s, Area: %.2f", color.colorize(getType()), getArea());
    }
}

package online.polp.shapes;

import online.polp.colorize.Color;

public class Circle extends Shape {
    private double radius;

    public Circle(Color color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String getType() {
        return "Circle";
    }

    @Override
    public void resize(double factor) throws InvalidFactorException {
        if (factor <= 0) {
            throw new InvalidFactorException();
        }

        radius *= factor;
    }
}

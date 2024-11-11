package online.polp.shapes;

import online.polp.colorize.Color;

public class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(Color color, double base, double height) {
        super(color);
        this.base = base;
        this.height = height;
    }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }

    @Override
    public String getType() {
        return "Triangle";
    }

    @Override
    public void resize(double factor) throws InvalidFactorException {
        if (factor <= 0) {
            throw new InvalidFactorException();
        }

        base *= factor;
        height *= factor;
    }
}

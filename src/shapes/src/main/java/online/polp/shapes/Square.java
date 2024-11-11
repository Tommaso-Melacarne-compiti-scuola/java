package online.polp.shapes;

import online.polp.colorize.Color;

public class Square extends Shape {
    private double side;

    public Square(Color color, double side) {
        super(color);
        this.side = side;
    }

    @Override
    public double getArea() {
        return Math.pow(side, 2);
    }

    @Override
    public String getType() {
        return "Square";
    }

    @Override
    public void resize(double factor) throws InvalidFactorException {
        if (factor <= 0) {
            throw new InvalidFactorException();
        }

        side *= factor;
    }
}

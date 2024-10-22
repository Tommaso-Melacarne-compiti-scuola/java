package online.polp;

public class Square extends GeometricFigure {
    private final double side;

    public Square(double side) {
        this.side = side;
    }

    public double calculateArea() {
        return side * side;
    }
}

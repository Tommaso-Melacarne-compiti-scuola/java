package online.polp;

public class Main {
    public static void main(String[] args) {
        GeometricFigure circle = new Circle(5);
        GeometricFigure square = new Square(5);

        System.out.println("Circle area: " + circle.calculateArea());
        System.out.println("Square area: " + square.calculateArea());
    }
}
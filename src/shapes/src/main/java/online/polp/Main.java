package online.polp;

import online.polp.colorize.Color;
import online.polp.shapes.Circle;
import online.polp.shapes.Shape;
import online.polp.shapes.Square;
import online.polp.shapes.Triangle;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ShapeManager shapeManager = new ShapeManager();

        shapeManager.addShape(new Square(Color.RED, 5));
        shapeManager.addShape(new Triangle(Color.BLUE, 3, 4));
        shapeManager.addShape(new Circle(Color.GREEN, 2));
        shapeManager.addShape(new Square(Color.YELLOW, 4));
        shapeManager.addShape(new Triangle(Color.CYAN, 2, 3));

        System.out.println("All shapes:");

        List<Shape> shapes = shapeManager.getShapes();

        for (Shape shape : shapes) {
            System.out.println(shape);
        }

        double allShapesArea = shapeManager.getAllShapesArea();
        System.out.printf("Total area: %.2f%n", allShapesArea);

        double factor = 2;
        shapeManager.resizeAllShapes(factor);

        double allShapesAreaAfterResizing = shapeManager.getAllShapesArea();
        System.out.printf("Total area after resizing by a factor of %.0f: %.2f%n", factor, allShapesAreaAfterResizing);
    }
}
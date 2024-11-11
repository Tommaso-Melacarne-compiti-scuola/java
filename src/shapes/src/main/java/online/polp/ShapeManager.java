package online.polp;

import online.polp.shapes.InvalidFactorException;
import online.polp.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class ShapeManager {
    List<Shape> shapes = new ArrayList<>();

    public List<Shape> getShapes() {
        return shapes;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public double getAllShapesArea() {
        return shapes.stream()
                .mapToDouble(Shape::getArea)
                .sum();
    }

    public void resizeAllShapes(double factor) {
        for (Shape shape : shapes) {
            try {
                shape.resize(factor);
            } catch (InvalidFactorException e) {
                System.out.println("Invalid factor: " + factor);
            }
        }
    }
}

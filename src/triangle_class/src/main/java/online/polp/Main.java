package online.polp;

public class Main {
    public static void main(String[] args) {
        Point[] trianglePoints = new Point[] {
                new Point(0, 0),
                new Point(3, 0),
                new Point(3, 4)
        };
        Triangle triangle = new Triangle(trianglePoints);
        // noinspection unused
        Triangle triangleCopy = new Triangle(triangle);
        System.out.println(triangle);
        System.out.println(triangle.calculatePerimeter());
        System.out.println(triangle.calculateArea());

        Square square = new Square(new Point[] {
                new Point(0, 0),
                new Point(3, 0),
                new Point(3, 3),
                new Point(0, 3)
        });
        // noinspection unused
        Square squareCopy = new Square(square);
        System.out.println(square);
        System.out.println(square.calculatePerimeter());
        System.out.println(square.calculateArea());
    }
}
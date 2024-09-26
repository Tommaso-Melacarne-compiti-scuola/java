package online.polp;

public class Main {
    public static void main(String[] args) {
        Point[] points = new Point[] {
                new Point(0, 0),
                new Point(3, 0),
                new Point(3, 4)
        };
        Triangle triangle = new Triangle(points);
        //noinspection unused
        Triangle triangleCopy = new Triangle(triangle);
        System.out.println(triangle.calculatePerimeter());
        System.out.println(triangle.calculateArea());
    }
}
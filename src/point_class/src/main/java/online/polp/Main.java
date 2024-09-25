package online.polp;

public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        System.out.println(p1.calculateDistance(p2));

        Point p3 = new Point(p1);
        System.out.println(p1.equals(p3));

        System.out.println(p1);
    }
}
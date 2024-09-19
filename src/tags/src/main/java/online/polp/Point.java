package online.polp;

public class Point {
    private double x;

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double z;

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double distanceFrom(Point point) {
        double a = Math.pow(point.getX() - x, 2);
        double b = Math.pow(point.getY() - y, 2);
        double c = Math.pow(point.getZ() - z, 2);

        return Math.sqrt(a + b + c);
    }
}

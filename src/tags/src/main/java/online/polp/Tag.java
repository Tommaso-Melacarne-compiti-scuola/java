package online.polp;

public class Tag {
    private String uid;
    private String description;
    private double distanceFromOrigin;
    private Point location;

    public Tag(String uid, String description, double distanceFromOrigin, Point location) {
        this.uid = uid;
        this.description = description;
        this.distanceFromOrigin = distanceFromOrigin;
        this.location = location;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDistanceFromOrigin() {
        return distanceFromOrigin;
    }

    public void setDistanceFromOrigin(double distanceFromOrigin) {
        this.distanceFromOrigin = distanceFromOrigin;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}

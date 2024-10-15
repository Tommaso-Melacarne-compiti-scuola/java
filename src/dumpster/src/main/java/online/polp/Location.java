package online.polp;

public class Location {
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }

        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isWithinArea(Location location1, Location location2) {
        return latitude >= location1.getLatitude() && latitude <= location2.getLatitude() &&
                longitude >= location1.getLongitude() && longitude <= location2.getLongitude();
    }
}

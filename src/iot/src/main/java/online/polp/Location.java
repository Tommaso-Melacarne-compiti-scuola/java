package online.polp;

import online.polp.utils.RandomUtils;

import java.util.Objects;

public class Location implements Comparable<Location> {
    private static final double MAX_LATITUDE = 90;
    private static final double MIN_LATITUDE = -90;
    private static final double MAX_LONGITUDE = 180;
    private static final double MIN_LONGITUDE = -180;

    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }

        this.latitude = latitude;
        this.longitude = longitude;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return latitude == location.latitude && longitude == location.longitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public static Location newRandomLocation() {
        double latitude = RandomUtils.random.nextDouble(MIN_LATITUDE, MAX_LATITUDE);
        double longitude = RandomUtils.random.nextDouble(MIN_LONGITUDE, MAX_LONGITUDE);
        
        return new Location(latitude, longitude);
    }

    /**
     Compares two locations based on their latitude and longitude
     Returns 0 if the locations are the same, 1 if this location is greater than the other location, -1 otherwise
    */
    @Override
    public int compareTo(Location other) {
        if (this.latitude == other.latitude && this.longitude == other.longitude) {
            return 0;
        } else if (this.latitude > other.latitude || this.longitude > other.longitude) {
            return 1;
        } else {
            return -1;
        }
    }
}

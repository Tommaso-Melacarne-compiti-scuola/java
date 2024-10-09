package online.polp;

public class Vehicle {
    private final int id;
    private String licensePlate;
    private String brand;
    private String model;
    private int seats;

    static private int nextId = 0;

    public Vehicle(String licensePlate, String brand, String model, int seats) {
        id = nextId;
        nextId++;

        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.seats = seats;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", licensePlate='" + licensePlate + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", seats=" + seats +
                '}';
    }
}

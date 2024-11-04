package online.polp.vehicles;

import java.time.LocalDateTime;

public abstract class Vehicle {
    private final String brand;
    private final String model;
    private final String licensePlate;
    private final LocalDateTime registrationDate;

    public Vehicle(String brand, String model, String licensePlate, LocalDateTime registrationDate) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.registrationDate = registrationDate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public abstract String getName();

    @Override
    public String toString() {
        return getName() + ": " + brand + " " + model + " " + licensePlate + " " + registrationDate;
    }
}

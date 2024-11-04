package online.polp.vehicles;

import java.time.LocalDateTime;

public class Truck extends Vehicle {
    public Truck(String brand, String model, String licensePlate, LocalDateTime registrationDate) {
        super(brand, model, licensePlate, registrationDate);
    }

    @Override
    public String getName() {
        return "Truck";
    }
}

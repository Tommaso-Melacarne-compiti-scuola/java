package online.polp.vehicles;

import java.time.LocalDateTime;

public class Car extends Vehicle {

    public Car(String brand, String model, String licensePlate, LocalDateTime registrationDate) {
        super(brand, model, licensePlate, registrationDate);
    }

    @Override
    public String getName() {
        return "Car";
    }
}

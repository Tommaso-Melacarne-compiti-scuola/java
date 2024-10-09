package online.polp;

import java.util.ArrayList;
import java.util.List;

public class VehicleManager {
    List<Vehicle> vehicles = new ArrayList<Vehicle>();

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicleById(int id) {
        vehicles.removeIf(vehicle -> vehicle.getId() == id);
    }

    public void removeVehicleByLicensePlate(String licensePlate) {
        vehicles.removeIf(vehicle -> vehicle.getLicensePlate().equals(licensePlate));
    }

    public Vehicle getVehicleById(int id) {
        return vehicles
                .stream()
                .filter(vehicle -> vehicle.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Vehicle getVehicleByLicensePlate(String licensePlate) {
        return vehicles
                .stream()
                .filter(vehicle -> vehicle.getLicensePlate().equals(licensePlate))
                .findFirst()
                .orElse(null);
    }

    public List<Vehicle> getVehiclesBySeats(int seats) {
        return vehicles
                .stream()
                .filter(vehicle -> vehicle.getSeats() == seats)
                .toList();
    }

    public VehicleInventoryBrandManager getVehicleInventory() {
        VehicleInventoryBrandManager carInventory = new VehicleInventoryBrandManager();

        vehicles
                .stream()
                .map(Vehicle::getBrand)
                .forEach(brand -> {
                    if (carInventory.containsBrand(brand)) {
                        carInventory.increaseBrandCount(brand);
                    } else {
                        carInventory.addBrand(brand);
                    }
                });

        return carInventory;
    }


    @Override
    public String toString() {
        return "VehicleManager{" +
                "vehicles=" + vehicles +
                '}';
    }
}

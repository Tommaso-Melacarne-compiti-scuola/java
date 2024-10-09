package online.polp;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        VehicleManager vehicleManager = new VehicleManager();

        // Add vehicles
        vehicleManager.addVehicle(new Vehicle("PLATE1", "BRAND1", "MODEL1", 5));
        vehicleManager.addVehicle(new Vehicle("PLATE2", "BRAND2", "MODEL2", 5));
        vehicleManager.addVehicle(new Vehicle("PLATE3", "BRAND1", "MODEL3", 5));
        vehicleManager.addVehicle(new Vehicle("PLATE4", "BRAND3", "MODEL4", 5));
        vehicleManager.addVehicle(new Vehicle("PLATE5", "BRAND1", "MODEL5", 5));
        vehicleManager.addVehicle(new Vehicle("PLATE6", "BRAND2", "MODEL6", 5));

        System.out.println("All vehicles:");
        vehicleManager.getVehicles().forEach(System.out::println);

        // Remove vehicles by id or license plate
        System.out.println("\nRemoving vehicles with id 1 and license plate PLATE2");
        vehicleManager.removeVehicleById(1);
        vehicleManager.removeVehicleByLicensePlate("PLATE2");

        // Get vehicles by id or license plate
        Vehicle vehicleId3 = vehicleManager.getVehicleById(3);
        System.out.println("\nVehicle with id 3: " + vehicleId3);
        Vehicle vehiclePlate4 = vehicleManager.getVehicleByLicensePlate("PLATE4");
        System.out.println("\nVehicle with license plate PLATE4: " + vehiclePlate4);

        // Get all vehicles by number of seats
        List<Vehicle> vehicle = vehicleManager.getVehiclesBySeats(5);
        System.out.println("\nVehicles with five seats:");
        vehicle.forEach(System.out::println);

        // Get vehicle inventory
        VehicleInventoryBrandManager vehicleInventory = vehicleManager.getVehicleInventory();
        System.out.println("\nVehicle inventory:");
        System.out.println(vehicleInventory);
    }
}
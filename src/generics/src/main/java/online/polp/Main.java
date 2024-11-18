package online.polp;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();

        Warehouse<ElectronicProduct> electronicProductWarehouse = new Warehouse<>();
        Warehouse<FoodProduct> foodProductWarehouse = new Warehouse<>();

        electronicProductWarehouse.addProduct(new ElectronicProduct(1, "Laptop", 1000, 24));
        electronicProductWarehouse.addProduct(new ElectronicProduct(1, "Smartphone", 500, 12));

        foodProductWarehouse.addProduct(new FoodProduct(1, "Apple", 1, now.plusDays(7)));
        foodProductWarehouse.addProduct(new FoodProduct(1, "Banana", 2, now.plusDays(3)));

        System.out.println(electronicProductWarehouse);
        System.out.println(foodProductWarehouse);

        // Add more laptops to the electronic product warehouse
        electronicProductWarehouse.addProductQuantityByName("Laptop", 5);
        System.out.println("Quantity of laptops increased by 5");

        // Get a laptop from the electronic product warehouse
        ElectronicProduct laptop = electronicProductWarehouse.getProductByName("Laptop");
        System.out.println(laptop);

        // Remove the smartphone from the electronic product warehouse
        electronicProductWarehouse.removeProductByName("Smartphone");

        System.out.println("Total quantity of electronic products: " + electronicProductWarehouse.getTotalQuantity());
        System.out.println("Total quantity of food products: " + foodProductWarehouse.getTotalQuantity());
    }
}
package online.polp;

import java.util.ArrayList;
import java.util.List;

public class VehicleInventoryBrandManager {
    List<VehicleInventoryBrand> vehicleInventoryBrands = new ArrayList<VehicleInventoryBrand>();

    public List<VehicleInventoryBrand> getCarInventoryBrands() {
        return vehicleInventoryBrands;
    }

    public boolean containsBrand(String brand) {
        return vehicleInventoryBrands
                .stream()
                .anyMatch(vehicleInventoryBrand -> vehicleInventoryBrand.getBrand().equals(brand));
    }

    public void addBrand(String brand) {
        vehicleInventoryBrands.add(new VehicleInventoryBrand(brand, 0));
    }

    public void increaseBrandCount(String brand) {
        vehicleInventoryBrands
                .stream()
                .filter(vehicleInventoryBrand -> vehicleInventoryBrand.getBrand().equals(brand))
                .findFirst()
                .ifPresent(vehicleInventoryBrand -> vehicleInventoryBrand.setCount(vehicleInventoryBrand.getCount() + 1));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Brand: Count\n");
        for (VehicleInventoryBrand vehicleInventoryBrand : vehicleInventoryBrands) {
            str.append(vehicleInventoryBrand).append("\n");
        }
        return str.toString();
    }
}

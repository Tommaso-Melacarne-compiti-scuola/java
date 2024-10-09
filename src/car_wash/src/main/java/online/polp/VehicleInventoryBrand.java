package online.polp;

public class VehicleInventoryBrand {
    private final String brand;
    private int count;

    public VehicleInventoryBrand(String brand, int count) {
        this.brand = brand;
        this.count = count;
    }

    public String getBrand() {
        return brand;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return brand + ", " + count;
    }
}

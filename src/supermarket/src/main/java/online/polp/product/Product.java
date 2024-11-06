package online.polp.product;

import online.polp.ProductType;

import java.time.LocalDateTime;

public abstract class Product {
    private final int id;
    private static int nextId = 0;
    private final String description;
    private final String price;

    public Product(String description, String price) {
        this.id = nextId++;
        this.description = description;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return description + " - " + price + " (type: " + getProductType() + ")";
    }

    public abstract ProductType getProductType();

    public abstract boolean isEligibleForDiscount(LocalDateTime now);
}

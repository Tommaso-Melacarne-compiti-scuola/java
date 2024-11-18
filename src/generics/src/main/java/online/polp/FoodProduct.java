package online.polp;

import java.time.LocalDate;

public class FoodProduct extends AbstractProduct {
    /**
     * The expiry date of the food product.
     */
    LocalDate expiryDate;


    /**
     * Creates a new food product with the given quantity, name, and expiry date.
     *
     * @param quantity   the quantity of the food product
     * @param name       the name of the food product
     * @param price      the price of the food product
     * @param expiryDate the expiry date of the food product
     */
    protected FoodProduct(int quantity, String name, double price, LocalDate expiryDate) {
        super(quantity, name, price);
        this.expiryDate = expiryDate;
    }

    public LocalDate getWarrantyPeriod() {
        return expiryDate;
    }

    @Override
    public String getType() {
        return "FoodProduct";
    }
}

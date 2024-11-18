package online.polp;

public interface ProductOperations {
    /**
     * Returns the name of the product.
     *
     * @return the name of the product.
     */
    String getName();

    /**
     * Returns the price of the product.
     *
     * @return the price of the product.
     */
    double getPrice();

    /**
     * Returns the quantity of the product.
     *
     * @return the quantity of the product.
     */
    int getQuantity();

    /**
     * Adds the given quantity to the product.
     *
     * @param quantity the quantity to add.
     */
    void addQuantity(int quantity);

    /**
     * Removes the given quantity from the product.
     *
     * @param quantity the quantity to remove.
     */
    void removeQuantity(int quantity);

    /**
     * Decreases the quantity of the product by 1.
     */
    void decreaseQuantity();
}

package online.polp;

public class ElectronicProduct extends AbstractProduct {
    /**
     * The warranty period of the electronic product in months.
     */
    int warrantyPeriod;

    /**
     * Creates a new electronic product with the given quantity, name, price, and warranty period.
     *
     * @param quantity       the quantity of the electronic product
     * @param name           the name of the electronic product
     * @param price          the price of the electronic product
     * @param warrantyPeriod the warranty period in months of the electronic product
     */
    protected ElectronicProduct(int quantity, String name, double price, int warrantyPeriod) {
        super(quantity, name, price);
        this.warrantyPeriod = warrantyPeriod;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    @Override
    public String getType() {
        return "ElectronicProduct";
    }
}

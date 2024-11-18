package online.polp;

public abstract class AbstractProduct implements ProductOperations {
    protected String name;
    protected double price;
    protected int quantity;

    protected AbstractProduct(int quantity, String name, double price) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public void removeQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public void decreaseQuantity() {
        removeQuantity(1);
    }

    public abstract String getType();

    @Override
    public String toString() {
        return getType() + "{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

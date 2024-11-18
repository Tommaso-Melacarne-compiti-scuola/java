package online.polp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Warehouse<T extends AbstractProduct> {
    HashMap<String, T> products = new HashMap<>();

    public void addProduct(T product) {
        // If the warehouse already contains the product, add the quantity to the existing product
        if (products.containsKey(product.getName())) {
            products.get(product.getName()).addQuantity(product.getQuantity());
            return;
        }

        products.put(product.getName(), product);
    }

    public T getProductByName(String name) {
        products.get(name).decreaseQuantity();

        return products.get(name);
    }

    public void addProductQuantityByName(String name, int quantity) {
        products.get(name).addQuantity(quantity);
    }

    public void removeProductByName(String name) {
        products.remove(name);
    }

    public List<T> getProducts() {
        return new ArrayList<>(products.values());
    }

    public int getTotalQuantity() {
        return products
                .values()
                .stream()
                .mapToInt(AbstractProduct::getQuantity)
                .sum();
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "products=" + products +
                '}';
    }
}

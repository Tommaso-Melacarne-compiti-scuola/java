package online.polp;

import online.polp.product.Product;

import java.time.LocalDateTime;
import java.util.List;

public record ShoppingList(List<Product> products) {
    private final static double extraDiscountForFood = 0.20;
    private final static double extraDiscountForNonFood = 0.10;

    /**
     * Calculates the discount for a product based on the client's type.
     *
     * @param client  the client buying the product
     * @param product the product to buy
     * @return the discount for the product expressed as a double between 0 and 1
     */
    private static double getDiscount(Client client, Product product, LocalDateTime now) {
        if (!client.hasCard()) {
            return 0;
        }

        boolean isProductEligibleForExtraDiscount = product.isEligibleForDiscount(now);

        if (!isProductEligibleForExtraDiscount) {
            // Base discount for cardholders
            return 0.05;
        }

        return switch (product.getProductType()) {
            case ProductType.FOOD -> extraDiscountForFood;
            case ProductType.NON_FOOD -> extraDiscountForNonFood;
        };
    }

    public double getTotalPrice(Client client, LocalDateTime now) {
        return products.stream()
                .mapToDouble(product -> {
                    double discount = getDiscount(client, product, now);
                    return Double.parseDouble(product.getPrice()) * (1 - discount);
                })
                .sum();
    }

    @Override
    public String toString() {
        return "ShoppingList: \n" + products;
    }
}


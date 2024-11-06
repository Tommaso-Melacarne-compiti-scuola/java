package online.polp;

import online.polp.product.FoodProduct;
import online.polp.product.NonFoodProduct;
import online.polp.product.Product;
import online.polp.product.ProductMaterialType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        Product glassPanel = new NonFoodProduct(
                "Glass panel",
                "100.0",
                ProductMaterialType.GLASS
        );

        Product metalChair = new NonFoodProduct(
                "Metal chair",
                "50.0",
                ProductMaterialType.METAL
        );

        Product apple = new FoodProduct("Apple", "1.0", now.plusDays(20));
        Product banana = new FoodProduct("Banana", "0.5", now.plusDays(10));


        // Create a new client with a card
        Client clientWithCard = new Client(true, new ShoppingList(new ArrayList<>(
                List.of(
                        glassPanel,
                        apple
                )
        )));

        // Create a new client without a card
        Client clientWithoutCard = new Client(false, new ShoppingList(new ArrayList<>(
                List.of(
                        metalChair,
                        banana
                )
        )));

        System.out.println(clientWithCard);
        double getTotalPrice = clientWithCard.shoppingList().getTotalPrice(clientWithCard, now);
        System.out.println("Total price for the client with the card: " + getTotalPrice);

        System.out.println(clientWithoutCard);
        double getTotalPrice2 = clientWithoutCard.shoppingList().getTotalPrice(clientWithoutCard, now);
        System.out.println("Total price for the client without the card: " + getTotalPrice2);
    }
}
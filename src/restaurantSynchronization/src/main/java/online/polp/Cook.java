package online.polp;

import lombok.RequiredArgsConstructor;
import online.polp.singletons.RandomSingleton;

import java.util.Random;

@RequiredArgsConstructor
public class Cook implements Runnable {
    private final String name;
    private final Tray tray;
    private final int dishesToPrepare;
    private final String[] menu = {"Pizza", "Pasta", "Salad", "Soup", "Steak", "Fish"};

    @Override
    public void run() {
        try {
            Random random = RandomSingleton.getInstance();

            for (int i = 0; i < dishesToPrepare; i++) {
                // Simulate cooking time using the singleton RandomProvider
                Thread.sleep(random.nextInt(500, 1000));
                
                // Create a new dish
                String dishName = menu[random.nextInt(menu.length)];
                Dish dish = new Dish(i, dishName);
                
                // Add dish to tray
                System.out.println(name + " is preparing " + dish.getName());
                tray.addDish(dish);
            }
            System.out.println(name + " has finished cooking all dishes.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(name + " was interrupted");
        }
    }
}

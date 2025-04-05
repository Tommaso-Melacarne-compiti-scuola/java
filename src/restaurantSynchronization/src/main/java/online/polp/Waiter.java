package online.polp;

import lombok.RequiredArgsConstructor;
import online.polp.singletons.RandomSingleton;

import java.util.Random;

@RequiredArgsConstructor
public class Waiter implements Runnable {
    private final String name;
    private final Tray tray;
    private final int dishesToServe;

    @Override
    public void run() {
        try {
            Random random = RandomSingleton.getInstance();

            int served = 0;
            while (served < dishesToServe) {
                Dish dish = tray.takeDish();
                
                if (dish == null) {
                    break;
                }
                
                Thread.sleep(random.nextInt(1000, 2000));
                
                System.out.println(name + " served " + dish.getName() + " to table");
                served++;
            }
            System.out.println(name + " has finished serving all dishes.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(name + " was interrupted");
        }
    }
}

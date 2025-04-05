package online.polp;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create shared tray
        Tray tray = new Tray(RestaurantConfig.TRAY_CAPACITY);
        
        // Create and start cook threads
        List<Thread> cookThreads = new ArrayList<>();
        for (int i = 0; i < RestaurantConfig.NUM_COOKS; i++) {
            Thread cookThread = new Thread(new Cook("Cook-" + (i+1), tray, RestaurantConfig.DISHES_PER_COOK));
            cookThreads.add(cookThread);
            cookThread.start();
        }
        
        // Create and start waiter threads
        List<Thread> waiterThreads = new ArrayList<>();
        for (int i = 0; i < RestaurantConfig.NUM_WAITERS; i++) {
            Thread waiterThread = new Thread(new Waiter("Waiter-" + (i+1), tray, RestaurantConfig.DISHES_PER_WAITER));
            waiterThreads.add(waiterThread);
            waiterThread.start();
        }
        
        // Wait for all cook threads to complete
        try {
            for (Thread t : cookThreads) {
                t.join();
            }
            
            // All cooks are done, initiate shutdown to unblock any waiting waiters
            tray.shutdown();
            
            // Wait for all waiter threads to complete
            for (Thread t : waiterThreads) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
            // Try to shutdown the tray to unblock any waiting threads
            tray.shutdown();
        }
        
        System.out.println("Restaurant simulation complete. Total dishes served: " + tray.getTotalServed());
    }
}

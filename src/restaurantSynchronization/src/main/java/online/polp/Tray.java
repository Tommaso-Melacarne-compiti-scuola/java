package online.polp;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tray {
    private final int maxCapacity;
    private final Queue<Dish> dishes;
    @Getter
    private int totalServed = 0;
    private boolean isShutdown = false;
    
    // Lock for the tray operations
    private final ReentrantLock lock = new ReentrantLock();
    // Condition for when the tray is not full
    private final Condition notFull = lock.newCondition();
    // Condition for when the tray is not empty
    private final Condition notEmpty = lock.newCondition();

    public Tray(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.dishes = new LinkedList<>();
    }

    /**
     * Cook adds a dish to the tray
     */
    public void addDish(Dish dish) throws InterruptedException {
        lock.lock();
        try {
            // Wait until there's space in the tray or shutdown
            while (dishes.size() >= maxCapacity && !isShutdown) {
                notFull.await();
            }
            
            // Check if we're shutting down
            if (isShutdown) {
                return;
            }
            
            // Add the dish
            dishes.add(dish);
            System.out.println("Cook added: " + dish + " - Tray size: " + dishes.size());
            
            // Signal that the tray is not empty
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Waiter takes a dish from the tray
     */
    public Dish takeDish() throws InterruptedException {
        lock.lock();
        try {
            // Wait until there's a dish in the tray or shutdown
            while (dishes.isEmpty() && !isShutdown) {
                notEmpty.await();
            }
            
            // Check if we're shutting down and tray is empty
            if (isShutdown && dishes.isEmpty()) {
                return null;
            }
            
            // Take a dish
            Dish dish = dishes.poll();
            totalServed++;
            System.out.println("Waiter took: " + dish + " - Tray size: " + dishes.size());
            
            // Signal that the tray is not full
            notFull.signal();
            
            return dish;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Shutdown the tray operations, waking up any waiting threads
     */
    public void shutdown() {
        lock.lock();
        try {
            isShutdown = true;
            // Wake up all waiting threads
            notEmpty.signalAll();
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

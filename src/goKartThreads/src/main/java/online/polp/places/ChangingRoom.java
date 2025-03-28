package online.polp.places;

import online.polp.colorize.Color;
import online.polp.singletons.RandomSingleton;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ChangingRoom {
    public static final int MIN_CHANGING_ROOM_TIME = 700;
    public static final int MAX_CHANGING_ROOM_TIME = 1000;

    private final Semaphore semaphore;

    public ChangingRoom(int capacity) {
        this.semaphore = new Semaphore(capacity, true); // Fair semaphore
    }

    public void use(int driverId) throws InterruptedException {
        Random random = RandomSingleton.getInstance();
        semaphore.acquire();
        
        long startTime = System.currentTimeMillis();
        
        Thread.sleep(random.nextLong(MIN_CHANGING_ROOM_TIME, MAX_CHANGING_ROOM_TIME));
        
        long duration = System.currentTimeMillis() - startTime;
        
        semaphore.release();
        Color.YELLOW.printWithColor("Driver " + driverId + " used the changing room for " + duration + " ms");
    }
}

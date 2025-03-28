package online.polp.places;

import online.polp.colorize.Color;
import online.polp.singletons.RandomSingleton;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Track {
    private static final int MIN_TRACK_TIME = 300;
    private static final int MAX_TRACK_TIME = 400;

    private final Semaphore semaphore;

    public Track(int capacity) {
        this.semaphore = new Semaphore(capacity, true);
    }

    public void use(int driverId, int numLaps, Color driverColor) throws InterruptedException {
        Random random = RandomSingleton.getInstance();

        semaphore.acquire();
        
        long startTime = System.currentTimeMillis();

        for (int lap = 1; lap <= numLaps; lap++) {
            Thread.sleep(random.nextLong(MIN_TRACK_TIME, MAX_TRACK_TIME));
        }

        long duration = System.currentTimeMillis() - startTime;

        semaphore.release();
        driverColor.printWithColor("Driver " + driverId + " used the track for " + duration + " ms");
    }
}

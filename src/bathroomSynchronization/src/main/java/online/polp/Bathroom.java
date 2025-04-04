package online.polp;

import online.polp.singletons.RandomSingleton;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bathroom {
    public static final int MIN_WAIT_TIME = 1000;
    public static final int MAX_WAIT_TIME = 2000;
    Lock lock = new ReentrantLock();

    public void use() {
        Random random = RandomSingleton.getInstance();

        lock.lock();

        try {
            Thread.sleep(random.nextInt(MIN_WAIT_TIME, MAX_WAIT_TIME));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}

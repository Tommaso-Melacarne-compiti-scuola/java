package online.polp;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
public class AtomicBoundedCounter {
    private final int MAX_COUNT;
    private int count;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            if (count < MAX_COUNT) {
                count++;
            }
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}

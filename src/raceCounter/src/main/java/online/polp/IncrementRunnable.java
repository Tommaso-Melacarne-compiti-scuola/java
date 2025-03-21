package online.polp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IncrementRunnable implements Runnable {
    private final AtomicBoundedCounter counter;
    private final int iterations;

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            counter.increment();
            System.out.println("Count: " + counter.getCount());
        }
    }
}

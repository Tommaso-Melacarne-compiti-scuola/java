package online.polp;

public class Main {
    public static void main(String[] args) {
        AtomicBoundedCounter counter = new AtomicBoundedCounter(100);

        Thread thread1 = new Thread(new IncrementRunnable(counter, 1000));

        Thread thread2 = new Thread(new IncrementRunnable(counter, 1000));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
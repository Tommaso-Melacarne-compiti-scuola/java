package online.polp;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new counterRunnable("Thread 1", 1, 10, 500));

        Thread thread2 = new Thread(new counterRunnable("Thread 2", 10, 1, 700));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
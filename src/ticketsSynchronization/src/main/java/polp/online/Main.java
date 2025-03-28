package polp.online;

import polp.online.singletons.RandomSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final int AVAILABLE_TICKETS = 10;
    private static final int THREADS_COUNT = 20;
    public static final int WAIT_TIME = 5;

    public static void main(String[] args) throws InterruptedException {
        // Ensure random singleton gets initialized
        //noinspection ResultOfMethodCallIgnored
        RandomSingleton.getInstance();

        TicketOffice ticketOffice = new TicketOffice(AVAILABLE_TICKETS);

        Thread consoleThread = new Thread(() -> {
            System.out.println(
                "Add a ticket at any time by writing the number of tickets to add and pressing ENTER");
            Scanner scanner = new Scanner(System.in);

            for (; ; ) {
                int scannedInt = scanner.nextInt();

                System.out.println("Adding " + scannedInt + " tickets");

                ticketOffice.addTickets(scannedInt);
            }
        });

        consoleThread.start();

        //noinspection InfiniteLoopStatement
        for (; ; ) {
            System.out.println("Start selling in " + WAIT_TIME + " seconds");
            System.out.println("Current ticket count is " + ticketOffice.getAvailableTickets());

            try {
                //noinspection BusyWait
                Thread.sleep(WAIT_TIME * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Restarting selling");

            List<Thread> threads = generateThreads(ticketOffice);

            for (Thread thread : threads) {
                thread.start();
            }

            for (Thread thread : threads) {
                thread.join();
            }
        }
    }

    private static List<Thread> generateThreads(TicketOffice ticketOffice) {
        List<Thread> threads = new ArrayList<>(THREADS_COUNT);

        for (int i = 0; i < THREADS_COUNT; i++) {
            UUID uuid = UUID.randomUUID();

            threads.add(new Thread(new TicketBuyer(ticketOffice, uuid)));
        }

        return threads;
    }
}
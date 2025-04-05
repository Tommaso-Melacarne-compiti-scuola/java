package online.polp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class PrimeFinder {
    public static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();

    public static List<Integer> findPrimes(int start, int end) {
        try (ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS)) {
            int range = (end - start + 1) / MAX_THREADS;
            List<Future<List<Integer>>> futures = new ArrayList<>();

            for (int i = 0; i < MAX_THREADS; i++) {
                int rangeStart = start + i * range;
                int rangeEnd = (i == MAX_THREADS - 1) ? end : rangeStart + range - 1;
                futures.add(executor.submit(new PrimeTask(rangeStart, rangeEnd)));
            }

            List<Integer> primes = new ArrayList<>();
            for (Future<List<Integer>> future : futures) {
                try {
                    primes.addAll(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            return primes;
        }
    }
}

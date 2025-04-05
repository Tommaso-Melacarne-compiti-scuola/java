package online.polp;

import java.util.List;

import static online.polp.utils.ListUtils.chunkList;

public class ParallelCalculator {
    static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();

    public static <T extends Number> T parallelReduceList(List<T> list, MathOperation<T> operation) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty");
        }

        if (list.size() == 1) {
            return list.getFirst();
        }

        int chunkSize = Math.max(1, list.size() / MAX_THREADS);
        List<List<T>> chunks = chunkList(list, chunkSize);

        List<ReducingRunnable<T>> reducingRunnables = chunks
            .stream()
            .map(chunk -> new ReducingRunnable<>(chunk, operation))
            .toList();

        List<Thread> threads = reducingRunnables
            .stream()
            .map(Thread::new)
            .toList();


        System.out.println("Starting " + threads.size() + " threads for parallel reduction.");
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrupted", e);
            }
        }

        List<T> results = reducingRunnables.stream()
                                           .map(ReducingRunnable::getResult)
                                           .toList();

        return reduceList(results, operation);
    }

    public static <T extends Number> T reduceList(List<T> list, MathOperation<T> operation) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty");
        }

        T result = list.getFirst();

        for (T element : list.subList(1, list.size())) {
            result = operation.calculate(result, element);
        }

        return result;
    }
}

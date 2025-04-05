package online.polp;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static online.polp.utils.ListUtils.chunkList;

public class ParallelCalculator {
    public static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();

    public static <T extends Number> T parallelReduceList(List<T> list, MathOperation<T> operation)  {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty");
        }

        if (list.size() == 1) {
            return list.getFirst();
        }

        int chunkSize = Math.max(1, list.size() / MAX_THREADS);
        List<List<T>> chunks = chunkList(list, chunkSize);

        List<T> results;

        try (ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS)) {
            List<Future<T>> futures = chunks
                .stream()
                .map(chunk -> executor.submit(new ReducingCallable<>(chunk, operation)))
                .toList();

            results = futures.stream()
                             .map(future -> {
                                 try {
                                     return future.get();
                                 } catch (InterruptedException | ExecutionException e) {
                                     throw new RuntimeException(
                                         "Error during parallel reduction",
                                         e
                                     );
                                 }
                             })
                             .toList();
        }

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

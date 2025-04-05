package online.polp;

import online.polp.singletons.RandomSingleton;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Main {
    public static final int SIZE = 100000000;

    public static void main(String[] args) {
        Random random = RandomSingleton.getInstance();

        List<Integer> randomNumbers = random.ints(SIZE, 1, Integer.MAX_VALUE).boxed().toList();

        MathOperation<Integer> operation = Integer::sum;

        benchmarkFunction(
            "Sequential",
            () -> ParallelCalculator.reduceList(randomNumbers, operation)
        );
        benchmarkFunction(
            "Parallel",
            () -> ParallelCalculator.parallelReduceList(randomNumbers, operation)
        );
        benchmarkFunction(
            "Standard Sequential",
            () -> randomNumbers.stream().reduce(0, operation::calculate)
        );
        benchmarkFunction(
            "Standard Parallel",
            () -> randomNumbers.parallelStream().reduce(0, operation::calculate)
        );
    }

    private static void benchmarkFunction(String name, Supplier<Integer> function) {
        long startTime = System.currentTimeMillis();
        Integer result = function.get();
        long endTime = System.currentTimeMillis();

        System.out.println(name + " took " + (endTime - startTime) + " ms. Result: " + result);
    }
}
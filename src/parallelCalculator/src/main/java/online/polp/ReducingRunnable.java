package online.polp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReducingRunnable<T extends Number> implements Runnable {
    private final List<T> list;
    private final MathOperation<T> operation;
    @Getter
    private T result;

    @Override
    public void run() {
        result = ParallelCalculator.reduceList(list, operation);
    }
}

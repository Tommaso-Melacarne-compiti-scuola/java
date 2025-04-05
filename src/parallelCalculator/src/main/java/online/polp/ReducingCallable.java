package online.polp;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.Callable;

@RequiredArgsConstructor
public class ReducingCallable<T extends Number> implements Callable<T> {
    private final List<T> list;
    private final MathOperation<T> operation;

    @Override
    public T call() {
        return ParallelCalculator.reduceList(list, operation);
    }
}
